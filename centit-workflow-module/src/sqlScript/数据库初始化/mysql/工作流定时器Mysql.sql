create  procedure consumeLifeTime() 
begin
 DECLARE nC int;
DECLARE consumeTime int DEFAULT 5;

-- ��Ҫ��������α����ݵı��� 
  DECLARE v_wfinstid varCHAR(64);
 -- �������ݽ�����־
  DECLARE done INT DEFAULT 0;
  -- �α�
  DECLARE r_f CURSOR FOR select wfinstid from  Wf_Flow_Instance where inststate = 'N' and isTimer='T';

  -- ��������־�󶨵��α�
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;  
  -- ���α�
  OPEN r_f;

  -- ��ʼѭ��
REPEAT  
    -- ��ȡ�α�������ݣ�����ֻ��һ��������Ļ�Ҳһ����
    FETCH r_f INTO v_wfinstid;

IF NOT done THEN  
    -- ��������������ѭ�����¼�

        update  Wf_Node_Instance set timelimit = timelimit - consumeTime
                where wfinstid= v_wfinstid and nodeState in('N','W') and istimer='T';

        update WF_STAGE_INSTANCE s set s.timelimit = s.timelimit - consumeTime
        where  s.wfinstid = v_wfinstid and s.stagecode in
               (select n.stagecode
                from Wf_Node_Instance i join wf_node n on (i.nodeid=n.nodeid)
                where i.wfinstid= v_wfinstid and i.nodeState in('N','W') and i.istimer='T'
                      and n.isaccounttime='T' and  n.istrunkline ='T');


        select count(1) into nC
          from Wf_Node_Instance i join wf_node n on (i.nodeid=n.nodeid)
          where i.wfinstid= v_wfinstid and i.nodeState in('N','W') and i.istimer='T'
                and n.isaccounttime='T' and  n.istrunkline ='T';

        if nC>0 then
          update Wf_Flow_Instance set timelimit = timelimit - consumeTime  where wfinstid= v_wfinstid ;
        end if;
END IF;  
 UNTIL done END REPEAT;  
  -- �ر��α�
  CLOSE r_f;
  end;