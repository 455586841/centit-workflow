package com.centit.workflow.po;

import com.centit.support.database.orm.GeneratorType;
import com.centit.support.database.orm.ValueGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create by scaffold
 *
 * @author codefan@hotmail.com
 */
@Entity
@Table(name = "WF_FLOW_INSTANCE_GROUP")
public class FlowInstanceGroup implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /** varchar(32) **/
    @Id
    @Column(name = "FLOW_GROUP_ID")
    @ValueGenerator(strategy = GeneratorType.UUID22)
    private String flowGroupId;

    /** varchar(200) **/
    @Column(name = "FLOW_GROUP_NAME")
    private String  flowGroupName;

    /** varchar(1000) **/
    @Column(name = "FLOW_GROUP_DESC")
    private String  flowGroupDesc;

    /**
     * default constructor
     */
    public FlowInstanceGroup() {
    }

    public String getFlowGroupId() {
        return flowGroupId;
    }

    public void setFlowGroupId(String flowGroupId) {
        this.flowGroupId = flowGroupId;
    }

    public String getFlowGroupName() {
        return flowGroupName;
    }

    public void setFlowGroupName(String flowGroupName) {
        this.flowGroupName = flowGroupName;
    }

    public String getFlowGroupDesc() {
        return flowGroupDesc;
    }

    public void setFlowGroupDesc(String flowGroupDesc) {
        this.flowGroupDesc = flowGroupDesc;
    }
}
