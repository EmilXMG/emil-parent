package com.wf.ew.system.controller;


import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.utils.StringUtil;
import com.wf.ew.system.entity.SysProject;
import com.wf.ew.system.service.ISysProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import com.wf.ew.common.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
@Controller
@RequestMapping("/system/sysProject")
public class SysProjectController extends BaseController {
    private final ISysProjectService sysProjectService;

    public SysProjectController(ISysProjectService sysProjectService) {
        this.sysProjectService = sysProjectService;
    }

    @RequiresPermissions("sysProject:view")
    @RequestMapping()
    public String sysProject() {
        return "system/pages/basic/data_manager/systemProject.html";
    }

    /**
     * 添加程序项目
     */
    @RequiresPermissions("sysProject:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(SysProject sysProject) {
        if (StringUtil.isBlank(sysProject.getChineseName())){
            return JsonResult.warning("项目中文名不能为空!");
        }
        if (StringUtil.isBlank(sysProject.getEnglishName())){
            return JsonResult.warning("项目英文名不能为空！");
        }
        if (StringUtil.isBlank(sysProject.getPath())){
            return JsonResult.warning("路径不能为空！");
        }
        sysProject.setCreateDate(new Date());
        if (sysProjectService.save(sysProject)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 程序项目列表
     **/
    @RequiresPermissions("sysProject:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<SysProject> list(HttpServletRequest request) {
        return sysProjectService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"create_date"}));
    }


    /**
     * 删除程序项目
     */
    @RequiresPermissions("sysProject:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer projectId) {
        if (sysProjectService.removeById(projectId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
