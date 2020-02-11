package com.wf.ew.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.expand.generatecode.database.DataTableOperate;
import com.wf.ew.common.expand.generatecode.generator.CodeGenerator;
import com.wf.ew.common.expand.generatecode.pojo.GenerateCode;
import com.wf.ew.system.entity.Datasource;
import com.wf.ew.system.entity.SysProject;
import com.wf.ew.system.entity.TableBasicinfo;
import com.wf.ew.system.service.CodeItemService;
import com.wf.ew.system.service.IDatasourceService;
import com.wf.ew.system.service.ISysProjectService;
import com.wf.ew.system.service.ITableBasicinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wf.ew.common.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author emil
 * @since 2019-08-14
 */
@Controller
@RequestMapping("/system/table-basicinfo")
public class TableBasicinfoController extends BaseController {

    private final ITableBasicinfoService tableBasicinfoService;

    private final IDatasourceService datasourceService;

    private final ISysProjectService sysProjectService;

    @Autowired
    private CodeItemService codeItemService;

    public TableBasicinfoController(ITableBasicinfoService tableBasicinfoService, IDatasourceService datasourceService, ISysProjectService sysProjectService) {
        this.tableBasicinfoService = tableBasicinfoService;
        this.datasourceService = datasourceService;
        this.sysProjectService = sysProjectService;
    }

    @RequiresPermissions("table-basicinfo:view")
    @RequestMapping()
    public String sysProject() {
        return "system/pages/basic/data_manager/systemTable/systemTableList.html";
    }

    @RequiresPermissions("table-basicinfo:view")
    @RequestMapping("/systemTableAddPage")
    public String datasourceAdd() {
        return "system/pages/basic/data_manager/systemTable/systemTableAdd.html";
    }


    @RequiresPermissions("table-basicinfo:view")
    @RequestMapping("/generateCodePage")
    public String generateCodePage() {
        return "system/pages/basic/data_manager/systemTable/generateCode.html";
    }

    @RequiresPermissions("table-basicinfo:view")
    @RequestMapping("/systemTableStructure")
    public String systemTableStructure() {
        return "system/pages/basic/data_manager/systemTable/systemTableStructure.html";
    }

    /**
     * 添加数据表
     */
    @RequiresPermissions("table-basicinfo:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(TableBasicinfo tableBasicinfo) {
        Datasource datasource = datasourceService.getById(tableBasicinfo.getDsId());
        DataTableOperate.createTable(tableBasicinfo.getTableName(), datasource.getDbName(), datasource.getServiceName(), datasource.getLoginUser(), datasource.getLoginPwd(), tableBasicinfo.getSqlTableName());
        tableBasicinfo.setCreateDate(new Date());
        tableBasicinfo.setTableGuid(UUID.randomUUID().toString());
        if (tableBasicinfoService.save(tableBasicinfo)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 数据表列表
     **/
    @RequiresPermissions("table-basicinfo:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<TableBasicinfo> list(HttpServletRequest request) {
        return tableBasicinfoService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"create_date"}));
    }

    /**
     * 数据表结构
     *
     * @param request
     * @return
     */
    @RequiresPermissions("table-basicinfo:view")
    @ResponseBody
    @RequestMapping("/listTableColumn")
    public PageResult<Map> listTableColumn(HttpServletRequest request) {
        return tableBasicinfoService.listTableColumn(new PageParam(request).setDefaultOrder(null, null));
    }

    /**
     * 删除数据源
     */
    @RequiresPermissions("table-basicinfo:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer tableId) {
        TableBasicinfo tableBasicinfo = tableBasicinfoService.getById(tableId);
        Datasource datasource = datasourceService.getById(tableBasicinfo.getDsId());
        DataTableOperate.deleteTable(datasource.getDbName(), datasource.getServiceName(), datasource.getLoginUser(), datasource.getLoginPwd(), tableBasicinfo.getSqlTableName());
        if (tableBasicinfoService.removeById(tableId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 获取数据源名和ID
     */
    @RequiresPermissions("table-basicinfo:view")
    @ResponseBody
    @RequestMapping("/getSystemProjectNameAndId")
    public String getSystemProjectNameAndId() {
        List<SysProject> sysProjects = sysProjectService.getSystemProjectNameAndId();
        return JSONObject.toJSONString(sysProjects);
    }


    /**
     * 获取程序项目中文名和英文名
     */
    @RequiresPermissions("table-basicinfo:view")
    @ResponseBody
    @RequestMapping("/getDsNameAndId")
    public String getDsNameAndId() {
        List<Datasource> datasourceList = datasourceService.getDsNameAndId();
        return JSONObject.toJSONString(datasourceList);
    }

    /**
     * 生成代码
     */
    @RequiresPermissions("table-basicinfo:update")
    @ResponseBody
    @RequestMapping(value = "/generateCode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public JsonResult generateCode(@RequestBody String param) {
        JSONObject jsonObject = JSONObject.parseObject(param);
        String tableId = jsonObject.getString("tableId");
        String projectPath = jsonObject.getString("projectPath");
        GenerateCode generateCode = tableBasicinfoService.getDataSourceAndTable(tableId);
        CodeGenerator.generateByTables(generateCode, projectPath);
        return JsonResult.ok("代码生成成功！");
    }
}
