package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.system.service.ISysDepartmentService;
import com.wf.ew.system.entity.SysDepartment;
import com.wf.ew.common.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import com.wf.ew.common.BaseController;

/**
 * 部门前端控制器
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-15
 */
@Controller
@RequestMapping("/system/sysDepartment")
public class SysDepartmentController extends BaseController {
    @Autowired
    private ISysDepartmentService sysDepartmentService;

    @RequiresPermissions("sysDepartment:view")
    @RequestMapping()
    public String datasourceList() {
        return "system/pages/basic/department/sysDepartmentList.html";
    }

    @RequiresPermissions("sysDepartment:view")
    @RequestMapping("/sysDepartmentAddPage")
    public String sysDepartmentAdd() {
        return "system/pages/basic/department/sysDepartmentAdd.html";
    }

    @RequiresPermissions("sysDepartment:view")
    @RequestMapping("/sysDepartmentEditPage")
    public String sysDepartmentEdit() {
        return "system/pages/basic/department/sysDepartmentEdit.html";
    }

    @RequiresPermissions("sysDepartment:view")
    @RequestMapping("/sysDepartmentDetailPage")
    public String sysDepartmentDetail() {
        return "system/pages/basic/department/sysDepartmentDetail.html";
    }

    /**
     * 分页查询部门
     */
    @RequiresPermissions("sysDepartment:view")
    @ResponseBody
    @RequestMapping("/sysDepartmentList")
    public PageResult<SysDepartment> sysDepartmentList(HttpServletRequest request) {
        return sysDepartmentService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"operateDate"}));
    }

    /**
     * 添加部门
     */
    @RequiresPermissions("sysDepartment:update")
    @ResponseBody
    @RequestMapping("/sysDepartmentAdd")
    public JsonResult sysDepartmentAdd(SysDepartment sysDepartment) {
        sysDepartment.setOperateDate(new Date());
        sysDepartment.setRowGuid(UUID.randomUUID().toString());
        sysDepartment.setOperateUserName(getLoginUser().getUsername());
        if (sysDepartmentService.save(sysDepartment)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除部门
     */
    @RequiresPermissions("sysDepartment:update")
    @ResponseBody
    @RequestMapping("/sysDepartmentDelete")
    public JsonResult sysDepartmentDelete(String rowGuid) {
        JSONArray rowGuidArray = JSONArray.parseArray(rowGuid);
        for (Object id : rowGuidArray) {
            sysDepartmentService.removeById((Serializable) id);
        }
        return JsonResult.ok("删除成功");
    }

    /**
     * 更新部门
     */
    @RequiresPermissions("sysDepartment:update")
    @ResponseBody
    @RequestMapping("/sysDepartmentUpdate")
    public JsonResult sysDepartmentUpdate(SysDepartment sysDepartment) {
        if (sysDepartmentService.updateById(sysDepartment)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据RowGuid获取部门
     */
    @RequiresPermissions("sysDepartment:view")
    @ResponseBody
    @RequestMapping("/getSysDepartmentByRowGuid")
    public SysDepartment getSysDepartmentByRowGuid(SysDepartment sysDepartment) {
        return sysDepartmentService.getById(sysDepartment.getRowGuid());
    }
}
