<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${table.comment!}新增</title>
    <% include("../../../layout/css.html"){} %>
</head>
<body>

<!-- 加载动画 -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>
<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form toolbar">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-input-inline mr0">
                            <input name="" class="layui-input" type="text" placeholder=""/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn icon-btn" lay-filter="${table.entityPath}Search" lay-submit>
                            <i class="layui-icon">&#xe615;</i>搜索
                        </button>
                    </div>
                    <!--删除数据-->
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
                    </div>
                </div>
            </div>
            <table class="layui-table" id="${table.entityPath}Table" lay-filter="${table.entityPath}Table"></table>
        </div>
    </div>
</div>
<!-- 表格操作列 -->
<script type="text/html" id="${table.entityPath}TableBar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>

<!-- js部分 -->
<% include("../../../layout/js.html"){} %>
<script>
    layui.use(['layer', 'form', 'table', 'util', 'admin', 'emil', 'tableX'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var util = layui.util;
        var admin = layui.admin;
        var emil = layui.emil;
        var tableX = layui.tableX;

        // 渲染表格
        var insTb = tableX.render({
            elem: '#${table.entityPath}Table',
            url: '${table.entityPath}/${table.entityPath}List',
            page: true,
            cellMinWidth: 100,
            toolbar: ['<p>',
                '<button lay-event="add" class="layui-btn layui-btn-sm icon-btn"><i class="layui-icon">&#xe654;</i>添加</button>&nbsp;',
                '<button lay-event="delete" class="layui-btn layui-btn-sm layui-btn-danger icon-btn"><i class="layui-icon">&#xe640;</i>删除</button>',
                '</p>'].join(''),
            height: 'full-100',
            cols: [[
                {type: 'checkbox'},
                <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
                {field: '${field.propertyName}', title: '${field.comment}', sort: true, align: 'center'},
                </#if>
                </#list>
                {align: 'center', toolbar: '#${table.entityPath}TableBar', width: 170, title: '操作'}
            ]]
        });

        // 搜索按钮点击事件
        form.on('submit(${table.entityPath}Search)', function (data) {
            insTb.reload({where: data.field}, 'data');
        });

        // 删除${table.comment!}

        function doDel(${table.entityPath}Id) {
            var itemId = [];
            itemId.push(${table.entityPath}Id);
            deletePost(itemId);
        }

        // 删除请求
        function deletePost(itemId) {
            layer.confirm('确定删除', {icon: 3, btnAlign: 'c', title: '提示信息'}, function (index) {
                $.post('${table.entityPath}/${table.entityPath}Delete', {
                    rowGuid: JSON.stringify(itemId)
                }, function (res) {
                    layer.closeAll('loading');
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon: 1});
                        insTb.reload();
                    } else {
                        layer.msg(res.msg, {icon: 2});
                    }
                }, 'json');
            });
        }

        // 表格工具条点击事件
        table.on('tool(${table.entityPath}Table)', function (obj) {
            if (obj.event === 'add') { //新增${table.comment!}
                var layIndex = admin.open({
                    type: 2,
                    title: "新增${table.comment!}",
                    shade: 0.7,
                    anim: 1,
                    area: ['700px', '500px'],
                    content: "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/${table.entityPath}AddPage",
                    end: function () {
                        if (admin.getLayerData(layIndex, 'formOk')) {  // 判断表单操作成功标识
                            insTb.reload();  // 成功刷新表格
                        }
                    }
                });
            } else if (obj.event === 'delete') { //批量删除${table.comment!}
                let checkStatus = table.checkStatus('${table.entityPath}Table'),
                    data = checkStatus.data,
                    itemId = [];
                if (data.length > 0) {
                    for (let i in data) {
                        itemId.push(data[i].rowGuid);
                    }
                    deletePost(itemId);
                } else {
                    layer.msg("请选择需要删除的数据");
                }
            }
        });

        // 工具条点击事件
        table.on('tool(${table.entityPath}Table)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') { // 修改
                var layIndex = admin.open({
                    type: 2,
                    title: "修改${table.comment!}",
                    shade: 0.7,
                    anim: 1,
                    area: ['700px', '500px'],
                    content: "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/${table.entityPath}EditPage?rowGuid=" + data.rowGuid,
                    end: function () {
                        if (admin.getLayerData(layIndex, 'formOk')) {  // 判断表单操作成功标识
                            insTb.reload();  // 成功刷新表格
                        }
                    }
                });
            } else if (layEvent === 'detail') {
                var layIndex = admin.open({
                    type: 2,
                    title: "${table.comment!}详情",
                    shade: 0.7,
                    anim: 1,
                    area: ['700px', '500px'],
                    content: "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/${table.entityPath}DetailPage?rowGuid=" + data.rowGuid
                });
            } else if (layEvent === 'delete') { // 删除
                doDel(data.rowGuid);
            }
        });
    });
</script>
</body>
</html>