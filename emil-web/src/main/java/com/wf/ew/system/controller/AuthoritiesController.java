package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.expand.dtree.DTree;
import com.wf.ew.common.expand.dtree.DTreeResponse;
import com.wf.ew.common.expand.dtree.Status;
import com.wf.ew.common.utils.StringUtil;
import com.wf.ew.system.entity.Authorities;
import com.wf.ew.system.service.AuthoritiesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author emil
 */
@Controller
@RequestMapping("/system/authorities")
public class AuthoritiesController extends BaseController {
    @Autowired
    private AuthoritiesService authoritiesService;

    @RequiresPermissions("authorities:view")
    @RequestMapping()
    public String authorities(Model model) {
        List<Authorities> authorities = authoritiesService.list(new QueryWrapper<Authorities>().eq("is_menu", 0).orderByAsc("order_number"));
        model.addAttribute("authorities", authorities);
        return "system/authorities.html";
    }

    /**
     * 查询所有权限
     **/
    @RequiresPermissions("authorities:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Authorities> list() {
        List<Authorities> authorities = authoritiesService.list(new QueryWrapper<Authorities>().orderByAsc("order_number"));
        return new PageResult<>(authorities);
    }

    /**
     * 添加权限
     */
    @RequiresPermissions("authorities:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(Authorities authorities) {
        if (authoritiesService.save(authorities)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改权限
     */
    @RequiresPermissions("authorities:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Authorities authorities) {
        if (authoritiesService.updateById(authorities)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除权限
     */
    @RequiresPermissions("authorities:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer authorityId) {
        if (authoritiesService.removeById(authorityId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 查询所有权限
     **/
    @RequiresPermissions("authorities:view")
    @ResponseBody
    @RequestMapping("/authoritiesTree")
    public String authoritiesTree() {
        List<Authorities> authorities = authoritiesService.getAuthorities();
        List<DTree> dTrees = getAuthoritiesTree(authorities, -1);
        DTreeResponse response = new DTreeResponse();
        response.setData(dTrees);
        response.setStatus(new Status());
        return JSONObject.toJSONString(response);
    }


    /**
     * 递归转化树形菜单
     */
    private List<DTree> getAuthoritiesTree(List<Authorities> authorities, Integer parentId) {
        List<DTree> dTrees = new ArrayList<>();
        DTree dTree;
        for (int i = 0; i < authorities.size(); i++) {
            Authorities temp = authorities.get(i);
            if (temp.getIsMenu() == 0 && parentId.intValue() == temp.getParentId().intValue()) {
                dTree = new DTree();
                dTree.setId(String.valueOf(temp.getAuthorityId()));
                dTree.setTitle(temp.getAuthorityName());
                dTree.setChildren(getAuthoritiesTree(authorities, authorities.get(i).getAuthorityId()));
                dTrees.add(dTree);
            }
        }
        return dTrees;
    }
}
