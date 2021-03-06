package com.centit.workflow.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.framework.common.JsonResultUtils;
import com.centit.framework.core.controller.BaseController;
import com.centit.framework.core.controller.WrapUpResponseBody;
import com.centit.framework.core.dao.PageQueryResult;
import com.centit.support.database.utils.PageDesc;
import com.centit.workflow.po.ApprRole;
import com.centit.workflow.po.ApprRoleDefine;
import com.centit.workflow.service.ApprRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/apprRole")
public class ApprRoleController extends BaseController {

    @Autowired
    private ApprRoleService apprRoleService;

    //审批角色列表
    @RequestMapping("/listApprRole")
    @WrapUpResponseBody
    public PageQueryResult<ApprRole> listAllApprRole(PageDesc pageDesc, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> filterMap = collectRequestParameters(request);
        List<ApprRole> objList = apprRoleService.listApprRoles(filterMap, pageDesc);
        return PageQueryResult.createResult(objList, pageDesc);
    }

    @RequestMapping(value = "/getApprRoleByCode", method = RequestMethod.GET)
    public void getApprRoleByCode(String roleCode, HttpServletRequest request, HttpServletResponse response){
        ApprRole apprRole = apprRoleService.getApprRoleByCode(roleCode);
        JsonResultUtils.writeSingleDataJson(apprRole,response);
    }

    @RequestMapping(value = "/saveApprRole", method = RequestMethod.POST)
    public void saveApprRole(@RequestBody ApprRole apprRole, HttpServletRequest request, HttpServletResponse response){
        apprRoleService.saveApprRole(apprRole);
        JsonResultUtils.writeBlankJson(response);
    }

    @RequestMapping("/deleteApprRoleByCode")
    public void deleteApprRoleByCode(String roleCode, HttpServletRequest request, HttpServletResponse response) {
        apprRoleService.deleteApprRoleByCode(roleCode);
        JsonResultUtils.writeSuccessJson(response);
    }

    /**
     * 获取审批角色的明细
     */
    @RequestMapping(value = {"/getApprRoleDefineListByCode/{roleCode}"}, method = {RequestMethod.GET})
    public void getApprRoleDefineListByCode(@PathVariable String roleCode, HttpServletResponse response) {
        List<ApprRoleDefine> apprRoleDefineList = this.apprRoleService.getApprRoleDefineListByCode(roleCode);
        ApprRole apprRole = new ApprRole();
        apprRole.setApprRoleDefineList(apprRoleDefineList);
        JsonResultUtils.writeSingleDataJson(apprRole, response);
    }

    @RequestMapping({"/deleteApprRoleDefineByCode"})
    public void deleteApprRoleDefineByCode(String id, HttpServletRequest request, HttpServletResponse response) {
        this.apprRoleService.deleteApprRoleDefineById(id);
        JsonResultUtils.writeSuccessJson(response);
    }

    @RequestMapping({"/saveApprRoleDefineList"})
    public void saveApprRoleDefineList(@RequestBody JSONObject paramData, HttpServletRequest request, HttpServletResponse response) {
        JSONArray apprRoleDefineList = paramData.getJSONArray("apprRoleDefineList");
        for(int i = 0; i < apprRoleDefineList.size(); ++i) {
            ApprRoleDefine apprRoleDefine = apprRoleDefineList.getObject(i, ApprRoleDefine.class);
            this.apprRoleService.saveApprRoleDefine(apprRoleDefine);
        }
        JsonResultUtils.writeBlankJson(response);
    }
}
