package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.system.service.ISysParamsService;
import com.wf.ew.system.entity.SysParams;
import com.wf.ew.common.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import com.wf.ew.common.BaseController;

/**
 * 系统参数前端控制器
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-08
 */
@Controller
@RequestMapping("/system/sysParams")
public class SysParamsController extends BaseController {
    @Autowired
    private ISysParamsService sysParamsService;

    @RequiresPermissions("sysParams:view")
    @RequestMapping()
    public String datasourceList() {
        return "system/pages/basic/systemParams/sysParamsList.html";
    }

    @RequiresPermissions("sysParams:view")
    @RequestMapping("/sysParamsAddPage")
    public String sysParamsAdd() {
        return "system/pages/basic/systemParams/sysParamsAdd.html";
    }

    @RequiresPermissions("sysParams:view")
    @RequestMapping("/sysParamsEditPage")
    public String sysParamsEdit() {
        return "system/pages/basic/systemParams/sysParamsEdit.html";
    }

    @RequiresPermissions("sysParams:view")
    @RequestMapping("/sysParamsDetailPage")
    public String sysParamsDetail() {
        return "system/pages/basic/systemParams/sysParamsDetail.html";
    }

    /**
     * 分页查询系统参数
     */
    @RequiresPermissions("sysParams:view")
    @ResponseBody
    @RequestMapping("/sysParamsList")
    public PageResult<SysParams> sysParamsList(HttpServletRequest request) {
        return sysParamsService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"operateDate"}));
    }

    /**
     * 添加系统参数
     */
    @RequiresPermissions("sysParams:update")
    @ResponseBody
    @RequestMapping("/sysParamsAdd")
    public JsonResult sysParamsAdd(SysParams sysParams) {
        sysParams.setOperateDate(new Date());
        sysParams.setRowGuid(UUID.randomUUID().toString());
        sysParams.setOperateUserName(getLoginUser().getUsername());
        sysParams.setUserId(getLoginUser().getUserId());
        List<SysParams> sysParamsList = sysParamsService.list();
        if (sysParamsList.size() > 0 && sysParamsList != null) {
            for (SysParams params : sysParamsList) {
                if (params.getParamName().equals(sysParams.getParamName())){
                    return JsonResult.warning("参数名不可重复！");
                }else {

                }
            }
        }
        if (sysParamsService.save(sysParams)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除系统参数
     */
    @RequiresPermissions("sysParams:update")
    @ResponseBody
    @RequestMapping("/sysParamsDelete")
    public JsonResult sysParamsDelete(String rowGuid) {
        JSONArray rowGuidArray = JSONArray.parseArray(rowGuid);
        for (Object id : rowGuidArray) {
            sysParamsService.removeById((Serializable) id);
        }
        return JsonResult.ok("删除成功");
    }

    /**
     * 更新系统参数
     */
    @RequiresPermissions("sysParams:update")
    @ResponseBody
    @RequestMapping("/sysParamsUpdate")
    public JsonResult sysParamsUpdate(SysParams sysParams) {
        if (sysParamsService.updateById(sysParams)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据RowGuid获取系统参数
     */
    @RequiresPermissions("sysParams:view")
    @ResponseBody
    @RequestMapping("/getSysParamsByRowGuid")
    public SysParams getSysParamsByRowGuid(SysParams sysParams) {
        return sysParamsService.getById(sysParams.getRowGuid());
    }
}
