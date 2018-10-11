package com.centit.workflow.dao;

import com.centit.framework.jdbc.dao.BaseDaoImpl;
import com.centit.framework.jdbc.dao.DatabaseOptUtils;
import com.centit.workflow.po.FlowOptDef;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by chen_rj on 2018-5-8.
 */
@Repository
public class FlowOptDefDao extends BaseDaoImpl<FlowOptDef,String> {
    @Override
    public Map<String, String> getFilterField() {
        return null;
    }

    public String getOptDefSequenceId() {
        Long optDefSequenceId = DatabaseOptUtils.getSequenceNextValue(this, "S_WFOPTDEF");
        return String.valueOf(optDefSequenceId);
    }
}
