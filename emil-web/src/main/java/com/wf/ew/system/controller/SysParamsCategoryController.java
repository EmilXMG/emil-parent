package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wf.ew.common.expand.dtree.DTree;
import com.wf.ew.common.expand.dtree.DTreeResponse;
import com.wf.ew.common.expand.dtree.Status;
import com.wf.ew.common.utils.StringUtil;
import com.wf.ew.system.entity.SysDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.system.service.ISysParamsCategoryService;
import com.wf.ew.system.entity.SysParamsCategory;
import com.wf.ew.common.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import com.wf.ew.common.BaseController;

/**
 * 系统参数种类前端控制器
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-16
 */
@Controller
@RequestMapping("/system/sysParamsCategory")
public class SysParamsCategoryController extends BaseController {
    @Autowired
    private ISysParamsCategoryService sysParamsCategoryService;

    /**
     * 分页查询系统参数种类
     */
    @RequiresPermissions("sysParamsCategory:view")
    @ResponseBody
    @RequestMapping("/sysParamsCategoryList")
    public PageResult<SysParamsCategory> sysParamsCategoryList(HttpServletRequest request) {
        return sysParamsCategoryService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"operateDate"}));
    }

    /**
     * 添加系统参数种类
     */
//    @RequiresPermissions("sysParamsCategory:update")
    @ResponseBody
    @RequestMapping("/sysParamsCategoryAdd")
    public JsonResult sysParamsCategoryAdd(SysParamsCategory sysParamsCategory) {
        sysParamsCategory.setOperateDate(new Date());
        sysParamsCategory.setRowGuid(UUID.randomUUID().toString());
        sysParamsCategory.setOperateUserName(getLoginUser().getUsername());
        if (sysParamsCategoryService.save(sysParamsCategory)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除系统参数种类
     */
//    @RequiresPermissions("sysParamsCategory:update")
    @ResponseBody
    @RequestMapping("/sysParamsCategoryDelete")
    public JsonResult sysParamsCategoryDelete(String rowGuid) {
        JSONArray rowGuidArray = JSONArray.parseArray(rowGuid);
        for (Object id : rowGuidArray) {
            sysParamsCategoryService.removeById((Serializable) id);
        }
        return JsonResult.ok("删除成功");
    }

    /**
     * 更新系统参数种类
     */
//    @RequiresPermissions("sysParamsCategory:update")
    @ResponseBody
    @RequestMapping("/sysParamsCategoryUpdate")
    public JsonResult sysParamsCategoryUpdate(SysParamsCategory sysParamsCategory) {
        if (sysParamsCategoryService.updateById(sysParamsCategory)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据RowGuid获取系统参数种类
     */
//    @RequiresPermissions("sysParamsCategory:view")
    @ResponseBody
    @RequestMapping("/getSysParamsCategoryByRowGuid")
    public SysParamsCategory getSysParamsCategoryByRowGuid(SysParamsCategory sysParamsCategory) {
        return sysParamsCategoryService.getById(sysParamsCategory.getRowGuid());
    }

    /**
     * 查询所有权限
     **/
    @ResponseBody
    @RequestMapping("/paramsCategoryTree")
    public String departmentTree() {
        List<SysParamsCategory> paramsCategoryList = sysParamsCategoryService.list();
        List<DTree> dTrees = getParamsCategoryTree(paramsCategoryList, "EMILROOT");
        List<DTree> rootTree = new ArrayList<>();
        DTree rootDtree = new DTree();
        rootDtree.setTitle("所有参数类型");
        rootDtree.setId("EMILROOT");
        rootDtree.setChildren(dTrees);
        rootTree.add(rootDtree);
        DTreeResponse response = new DTreeResponse();
        response.setData(rootTree);
        response.setStatus(new Status());
        return JSONObject.toJSONString(response);
    }


    /**
     * 递归转化树形菜单
     */
    private List<DTree> getParamsCategoryTree(List<SysParamsCategory> paramsCategory, String parentId) {
        List<DTree> dTrees = new ArrayList<>();
        DTree dTree;
        for (int i = 0; i < paramsCategory.size(); i++) {
            SysParamsCategory temp = paramsCategory.get(i);
            if (parentId.equals( temp.getParentId())) {
                dTree = new DTree();
                dTree.setId(temp.getRowGuid());
                dTree.setTitle(temp.getCategoryName());
                dTree.setSpread(true);
                dTree.setChildren(getParamsCategoryTree(paramsCategory, paramsCategory.get(i).getRowGuid()));
                dTrees.add(dTree);
            }
        }
        return dTrees;
    }
}
