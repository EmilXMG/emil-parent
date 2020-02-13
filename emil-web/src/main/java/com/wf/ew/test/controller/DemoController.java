package com.wf.ew.test.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.test.service.IDemoService;
import com.wf.ew.test.entity.Demo;
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
* 测试前端控制器
*
* @author emil
* @since 2020-02-11
* @version v1.0
*/
@Controller
@RequestMapping("/test/demo")
public class DemoController extends BaseController {
    @Autowired
    private IDemoService demoService;

    @RequiresPermissions("demo:view")
    @RequestMapping()
    public String datasourceList() {
    return "system/pages/test/demoList.html";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/demoAddPage")
    public String demoAdd() {
    return "system/pages/test/demoAdd.html";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/demoEditPage")
    public String demoEdit() {
    return "system/pages/test/demoEdit.html";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/demoDetailPage")
    public String demoDetail() {
    return "system/pages/test/demoDetail.html";
    }

    /**
    * 分页查询测试
    */
    @RequiresPermissions("demo:view")
    @ResponseBody
    @RequestMapping("/demoList")
    public PageResult<Demo> demoList(HttpServletRequest request) {
    return demoService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"operateDate"}));
    }

    /**
    * 添加测试
    */
    @RequiresPermissions("demo:update")
    @ResponseBody
    @RequestMapping("/demoAdd")
    public JsonResult demoAdd(Demo demo) {
    demo.setOperateDate(new Date());
    demo.setRowGuid(UUID.randomUUID().toString());
    demo.setOperateUserName(getLoginUser().getUsername());
    if (demoService.save(demo)) {
    return JsonResult.ok("添加成功");
    }
    return JsonResult.error("添加失败");
    }

    /**
    * 删除测试
    */
    @RequiresPermissions("demo:update")
    @ResponseBody
    @RequestMapping("/demoDelete")
    public JsonResult demoDelete(String rowGuid) {
    JSONArray rowGuidArray = JSONArray.parseArray(rowGuid);
    for (Object id : rowGuidArray) {
        demoService.removeById((Serializable) id);
    }
    return JsonResult.ok("删除成功");
    }

    /**
    * 更新测试
    */
    @RequiresPermissions("demo:update")
    @ResponseBody
    @RequestMapping("/demoUpdate")
    public JsonResult demoUpdate(Demo demo) {
    if (demoService.updateById(demo)) {
    return JsonResult.ok("修改成功");
    }
    return JsonResult.error("修改失败");
    }

    /**
    * 根据RowGuid获取测试
    */
    @RequiresPermissions("demo:view")
    @ResponseBody
    @RequestMapping("/getDemoByRowGuid")
    public Demo getDemoByRowGuid(Demo demo) {
    return demoService.getById(demo.getRowGuid());
    }
    }
