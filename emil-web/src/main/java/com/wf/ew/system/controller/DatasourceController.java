package com.wf.ew.system.controller;


import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.database.DatabaseConnection;
import com.wf.ew.system.entity.Datasource;
import com.wf.ew.system.service.IDatasourceService;
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
@RequestMapping("/system/datasource")
public class DatasourceController extends BaseController {

    private final IDatasourceService datasourceService;

    public DatasourceController(IDatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    @RequiresPermissions("datasource:view")
    @RequestMapping()
    public String datasourceList() {
        return "system/pages/basic/data_manager/datasource/datasourceList.html";
    }


    @RequiresPermissions("datasource:view")
    @RequestMapping("/datasourceAddPage")
    public String datasourceAdd() {
        return "system/pages/basic/data_manager/datasource/datasourceAdd.html";
    }

    @RequiresPermissions("datasource:view")
    @RequestMapping("/datasourceEditPage")
    public String datasourceEdit() {
        return "system/pages/basic/data_manager/datasource/datasourceEdit.html";
    }


    /**
     *添加数据源
     */
    @RequiresPermissions("datasource:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(Datasource datasource) {
        datasource.setCreateDate(new Date());
        if (datasourceService.save(datasource)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 数据源列表
     **/
    @RequiresPermissions("datasource:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Datasource> list(HttpServletRequest request) {
        return datasourceService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"create_date"}));
    }

    /**
     * 删除数据源
     */
    @RequiresPermissions("datasource:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer dsId) {
        if (datasourceService.removeById(dsId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     *根据ID获取数据原信息
     */
    @RequiresPermissions("datasource:view")
    @ResponseBody
    @RequestMapping("/getInfoById")
    public Datasource getInfoById(Datasource datasourceParam) {
         return datasourceService.getById(datasourceParam.getDsId());
    }

    /**
     * 测试数据库是否连接成功
     */
    @RequiresPermissions("datasource:view")
    @ResponseBody
    @RequestMapping("/testConntection")
    public JsonResult testConntection(Datasource datasourceParam) {
        Datasource datasource = datasourceService.getById(datasourceParam.getDsId());
        boolean status = DatabaseConnection.DatabaseConnectionTest(datasource.getDbName(), datasource.getServiceName(), datasource.getLoginUser(), datasource.getLoginPwd());
        if (status) {
            return JsonResult.ok("连接成功");
        }
        return JsonResult.error("连接失败");

    }

    /**
     *更新数据源
     */
    @RequiresPermissions("datasource:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Datasource datasource) {
        if (datasourceService.updateById(datasource)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
