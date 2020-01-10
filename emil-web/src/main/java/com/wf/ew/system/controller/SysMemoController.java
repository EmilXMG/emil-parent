package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.system.service.ISysMemoService;
import com.wf.ew.system.entity.SysMemo;
import com.wf.ew.common.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import com.wf.ew.common.BaseController;

/**
 * 便签信息前端控制器
 *
 * @author emil
 * @version v1.0
 * @since 2019-09-14
 */
@Controller
@RequestMapping("/tpl/sysMemo")
public class SysMemoController extends BaseController {
    @Autowired
    private ISysMemoService sysMemoService;

    @RequiresPermissions("sysMemo:view")
    @RequestMapping()
    public String datasourceList() {
        return "system/pages/system/sysMemoList.html";
    }

    @RequiresPermissions("sysMemo:view")
    @RequestMapping("/sysMemoAddPage")
    public String sysMemoAdd() {
        return "system/pages/system/sysMemoAdd.html";
    }

    @RequiresPermissions("sysMemo:view")
    @RequestMapping("/sysMemoEditPage")
    public String sysMemoEdit() {
        return "system/pages/system/sysMemoEdit.html";
    }

    @RequiresPermissions("sysMemo:view")
    @RequestMapping("/sysMemoDetailPage")
    public String sysMemoDetail() {
        return "system/pages/system/sysMemoDetail.html";
    }

    /**
     * 分页查询便签信息
     */
    @ResponseBody
    @RequestMapping("/sysMemoList")
    public PageResult<SysMemo> sysMemoList(HttpServletRequest request) {
        return sysMemoService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"operateDate"}));
    }

    /**
     * 添加便签信息
     */
    @ResponseBody
    @RequestMapping("/sysMemoAdd")
    public JsonResult sysMemoAdd(SysMemo sysMemo) {
        sysMemo.setOperateDate(new Date());
        sysMemo.setRowGuid(UUID.randomUUID().toString());
        sysMemo.setOperateUserName(getLoginUser().getUsername());
        sysMemo.setUserId(getLoginUserId());
        sysMemo.setCreateDate(new Date());
        if (sysMemoService.save(sysMemo)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除便签信息
     */
    @ResponseBody
    @RequestMapping("/sysMemoDelete")
    public JsonResult sysMemoDelete(String rowGuid) {
        if (sysMemoService.removeById(rowGuid)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 更新便签信息
     */
    @ResponseBody
    @RequestMapping("/sysMemoUpdate")
    public JsonResult sysMemoUpdate(SysMemo sysMemo) {
        if (sysMemoService.updateById(sysMemo)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据RowGuid获取便签信息
     */
    @ResponseBody
    @RequestMapping("/getSysMemoByRowGuid")
    public SysMemo getSysMemoByRowGuid(SysMemo sysMemo) {
        return sysMemoService.getById(sysMemo.getRowGuid());
    }


    /**
     * 根据用户ID获取便签信息
     */
    @ResponseBody
    @RequestMapping("/getSysMemoByUserId")
    public String getSysMemoByUserId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", sysMemoService.getSysMemoByUserId(getLoginUserId()));
        return jsonObject.toJSONString();
    }
}
