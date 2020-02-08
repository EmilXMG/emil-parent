<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${table.comment!}修改</title>
    <% include("../../../layout/css.html"){} %>
</head>
<body>
<!-- 加载动画 -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>
<form class="layui-form model-form" lay-filter="${table.entityPath}Form">
    <input type="text" name="rowGuid" class="layui-input layui-hide">
    <div class="layui-form-item">
        <#list table.fields as field>
            <#if !field.keyFlag><#--生成普通字段 -->
                <div class="layui-inline">
                    <label class="layui-form-label">${field.comment}：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="${field.propertyName}" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </#if>
        </#list>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="${table.entityPath}Edit">修改</button>
            <button class="layui-btn  layui-btn-primary" ew-event="closeDialog">取消</button>
        </div>
    </div>
</form>
<!-- js部分 -->
<% include("../../../layout/js.html"){} %>
<script>
    layui.use(['layer', 'form', 'table', 'util', 'admin','emil'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var util = layui.util;
        var admin = layui.admin;
        var emil = layui.emil;

        var rowGuid = emil.getUrlParams('rowGuid');
        $('#rowGuid').val(rowGuid);

        //渲染${table.comment!}
        $.post("get${entity}ByRowGuid", {rowGuid: rowGuid}, function (res) {
            form.val('${table.entityPath}Form', res);
            form.render();
        }, 'json');

        // 修改${table.comment!}
        form.on("submit(${table.entityPath}Edit)", function (data) {
            layer.load(2);
            $.post("${table.entityPath}Update", data.field, function (res) {
                layer.closeAll('loading');
                if (res.code == 200) {
                    layer.msg(res.msg, {icon: 1});
                    setTimeout(function () {
                        parent.location.reload();
                    }, 500);
                } else if (res.code == 500) {
                    layer.msg(res.msg, {icon: 2});
                }
            }, 'json');
            return false;
        })
    });
</script>
</body>
</html>