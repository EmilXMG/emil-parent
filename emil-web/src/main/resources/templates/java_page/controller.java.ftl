package ${package.Controller};

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.${package.ModuleName}.service.${table.serviceName};
import com.wf.ew.${package.ModuleName}.entity.${entity};
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
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
* ${table.comment!}前端控制器
*
* @author ${author}
* @since ${date}
* @version v1.0
*/
<#if restControllerStyle>
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    @Autowired
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};

    @RequiresPermissions("${table.entityPath}:view")
    @RequestMapping()
    public String datasourceList() {
    return "system/pages/${package.ModuleName}/${table.entityPath}List.html";
    }

    @RequiresPermissions("${table.entityPath}:view")
    @RequestMapping("/${table.entityPath}AddPage")
    public String ${table.entityPath}Add() {
    return "system/pages/${package.ModuleName}/${table.entityPath}Add.html";
    }

    @RequiresPermissions("${table.entityPath}:view")
    @RequestMapping("/${table.entityPath}EditPage")
    public String ${table.entityPath}Edit() {
    return "system/pages/${package.ModuleName}/${table.entityPath}Edit.html";
    }

    @RequiresPermissions("${table.entityPath}:view")
    @RequestMapping("/${table.entityPath}DetailPage")
    public String ${table.entityPath}Detail() {
    return "system/pages/${package.ModuleName}/${table.entityPath}Detail.html";
    }

    /**
    * 分页查询${table.comment!}
    */
    @RequiresPermissions("${table.entityPath}:view")
    @ResponseBody
    @RequestMapping("/${table.entityPath}List")
    public PageResult<${entity}> ${table.entityPath}List(HttpServletRequest request) {
    return ${(table.serviceName?substring(1))?uncap_first}.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"operateDate"}));
    }

    /**
    * 添加${table.comment!}
    */
    @RequiresPermissions("${table.entityPath}:update")
    @ResponseBody
    @RequestMapping("/${table.entityPath}Add")
    public JsonResult ${table.entityPath}Add(${entity} ${entity?uncap_first}) {
    ${entity?uncap_first}.setOperateDate(new Date());
    ${entity?uncap_first}.setRowGuid(UUID.randomUUID().toString());
    ${entity?uncap_first}.setOperateUserName(getLoginUser().getUsername());
    if (${(table.serviceName?substring(1))?uncap_first}.save(${entity?uncap_first})) {
    return JsonResult.ok("添加成功");
    }
    return JsonResult.error("添加失败");
    }

    /**
    * 删除${table.comment!}
    */
    @RequiresPermissions("${table.entityPath}:update")
    @ResponseBody
    @RequestMapping("/${table.entityPath}Delete")
    public JsonResult ${table.entityPath}Delete(String rowGuid) {
    JSONArray rowGuidArray = JSONArray.parseArray(rowGuid);
    for (Object id : rowGuidArray) {
        ${(table.serviceName?substring(1))?uncap_first}.removeById((Serializable) id);
    }
    return JsonResult.ok("删除成功");
    }

    /**
    * 更新${table.comment!}
    */
    @RequiresPermissions("${table.entityPath}:update")
    @ResponseBody
    @RequestMapping("/${table.entityPath}Update")
    public JsonResult ${table.entityPath}Update(${entity} ${table.entityPath}) {
    if (${(table.serviceName?substring(1))?uncap_first}.updateById(${table.entityPath})) {
    return JsonResult.ok("修改成功");
    }
    return JsonResult.error("修改失败");
    }

    /**
    * 根据RowGuid获取${table.comment!}
    */
    @RequiresPermissions("${table.entityPath}:view")
    @ResponseBody
    @RequestMapping("/get${entity}ByRowGuid")
    public ${entity} get${entity}ByRowGuid(${entity} ${table.entityPath}) {
    return ${(table.serviceName?substring(1))?uncap_first}.getById(${table.entityPath}.getRowGuid());
    }
    }
</#if>