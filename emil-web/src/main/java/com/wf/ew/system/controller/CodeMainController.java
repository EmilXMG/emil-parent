package com.wf.ew.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.dtree.DTree;
import com.wf.ew.common.dtree.DTreeResponse;
import com.wf.ew.common.dtree.Status;
import com.wf.ew.common.utils.StringUtil;
import com.wf.ew.system.entity.CodeMain;
import com.wf.ew.system.entity.Subsystem;
import com.wf.ew.system.service.CodeItemService;
import com.wf.ew.system.service.CodeMainService;
import com.wf.ew.system.service.SubsystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author emil
 */
@Controller
@RequestMapping("/system/codeMain")
public class CodeMainController {

    private final CodeMainService codeMainService;

    private final SubsystemService subsystemService;

    private final CodeItemService codeItemService;

    @Autowired
    public CodeMainController(CodeMainService codeMainService, SubsystemService subsystemService, CodeItemService codeItemService) {
        this.codeMainService = codeMainService;
        this.subsystemService = subsystemService;
        this.codeItemService = codeItemService;
    }

    @RequiresPermissions("codeMain:view")
    @RequestMapping()
    public String codeMain() {
        return "system/pages/basic/data_manager/codeMain.html";
    }

    @RequiresPermissions("codeMain:view")
    @ResponseBody
    @RequestMapping("/dtree")
    public String dtree() {
        List<DTree> dTrees = new ArrayList<>();
        List<DTree> children = new ArrayList<>();
        List<Subsystem> subsystems = subsystemService.getSubsystemNameAndNum();
        DTree rootTree = new DTree();
        rootTree.setId("EMILROOT");
        rootTree.setTitle("所有子系统");
        DTree dTree;
        for (Subsystem subsystem : subsystems) {
            dTree = new DTree();
            dTree.setParentId(rootTree.getId());
            dTree.setId(subsystem.getSubsystemNum());
            dTree.setTitle(subsystem.getSubsystemName());
            children.add(dTree);
        }
        dTrees.add(rootTree);
        DTreeResponse dTreeResponse = new DTreeResponse();
        rootTree.setChildren(children);
        dTreeResponse.setData(dTrees);
        dTreeResponse.setStatus(new Status());
        return JSONObject.toJSONString(dTreeResponse);
    }


    /**
     * 添加代码项
     **/
    @RequiresPermissions("codeMain:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(CodeMain codeMain) {
        List<CodeMain> codeMains = codeMainService.getSubsystemName();
        for (CodeMain codeMainName : codeMains) {
            if (codeMainName.getCodeName().equals(codeMain.getCodeName())) {
                return JsonResult.warning("该代码项已存在!");
            }
        }
        if (StringUtil.isBlank(codeMain.getCodeName())) {
            return JsonResult.warning("请输入代码项名称！");
        }
        codeMain.setCreateDate(new Date());
        if (codeMainService.save(codeMain)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除代码项
     */
    @RequiresPermissions("codeMain:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer codeId) {
        int codeItemCount = codeItemService.getCodeItemCount(codeId);
        if (codeItemCount > 0) {
            return JsonResult.warning("该代码项有子项不可删除！");
        } else {
            if (codeMainService.removeById(codeId)) {
                return JsonResult.ok("删除成功！");
            }
            return JsonResult.error("删除失败！");
        }
    }

    /**
     * 代码项列表
     **/
    @RequiresPermissions("codeMain:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<CodeMain> list(HttpServletRequest request) {
        return codeMainService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"create_date"}));
    }

    /**
     * 更新代码项
     */
    @RequiresPermissions("codeMain:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(CodeMain codeMain) {
        List<CodeMain> codeMains = codeMainService.getSubsystemName();
        for (CodeMain codeMainName : codeMains) {
            if (codeMainName.getCodeName().equals(codeMain.getCodeName())) {
                return JsonResult.warning("该代码项已存在!");
            }
        }
        if (codeMainService.updateById(codeMain)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
