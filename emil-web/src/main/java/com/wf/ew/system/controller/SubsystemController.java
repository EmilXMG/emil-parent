package com.wf.ew.system.controller;


import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.utils.StringUtil;
import com.wf.ew.system.entity.Subsystem;
import com.wf.ew.system.service.SubsystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author emil
 */
@Controller
@RequestMapping("/system/subsystem")
public class SubsystemController {
    @Autowired
    private SubsystemService subsystemService;


    @RequiresPermissions("subsystem:view")
    @RequestMapping()
    public String subsystem() {
        return "system/pages/basic/data_manager/subsystem.html";
    }

    /**
     * 添加子系统
     **/
    @RequiresPermissions("subsystem:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(Subsystem subsystem) {
        if (StringUtil.isBlank(subsystem.getSubsystemName())) {
            return JsonResult.warning("请输入子系统名!");
        }
        List<Subsystem> subsystemNames = subsystemService.getSubsystemName();
        for (Subsystem subsystemName : subsystemNames) {
            if (subsystemName.getSubsystemName().equals(subsystem.getSubsystemName())) {
                return JsonResult.warning("该子系统已存在!");
            }
        }
        String subsystemNum = subsystemService.getSubsystemNum();
        if (StringUtil.isBlank(subsystemNum)) {
            subsystem.setSubsystemNum("01");
        } else {
            int i = Integer.parseInt(subsystemNum.substring(subsystemNum.length() - 1));
            i += 1;
            subsystem.setSubsystemNum("0" + i);
            if (i > 10) {
                return JsonResult.error("最多只能添加10条数据");
            }
        }
        if (StringUtil.isBlank(subsystem.getOrderNum())) {
            subsystem.setOrderNum("0");
        }
        subsystem.setCreateDate(new Date());
        if (subsystemService.save(subsystem)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 子系统列表
     **/
    @RequiresPermissions("subsystem:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Subsystem> list(HttpServletRequest request) {
        return subsystemService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"order_num"}));
    }

    /**
     * 删除子系统
     */
    @RequiresPermissions("subsystem:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer subsystemId) {
        System.out.println(subsystemId);
        if (subsystemService.removeById(subsystemId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 更新子系统
     */
    @RequiresPermissions("subsystem:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Subsystem subsystem) {
        List<Subsystem> subsystemNames = subsystemService.getSubsystemName();
        for (Subsystem subsystemName : subsystemNames) {
            if (subsystemName.getSubsystemName().equals(subsystem.getSubsystemName())) {
                return JsonResult.warning("该子系统已存在!");
            }
        }
        if (subsystemService.updateById(subsystem)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
