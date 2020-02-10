layui.define(["jquery", "layer", 'admin'], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var admin = layui.admin;
    let emil = {

        /**
         * 根据name序列化表单数据
         * @param id
         * @returns {*|jQuery}
         */
        getJsonString: function (id) {
            let itemId = '#' + id;
            return $(itemId).serialize()
        },

        /**
         * 获取项目路径
         * @returns {Location | string | any}
         */
        getRootPath: function () {
            let fullPath = window.document.location
            return fullPath;
        },

        /**
         * 获取请求地址参数
         * @param name 参数名
         * @returns {string} 参数值
         */
        getUrlParams: function (name) {
            let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            let r = window.location.search.substr(1).match(reg);
            if (r !== null) {
                return decodeURI(r[2]);
            }
            return '';
        },

        /**
         * 定时执行方法
         * @param delay 时间间隔
         * @param fn 执行函数
         */
        timingFn: function (delay, fn) {
            fn();
            setTimeout(function () {
                Util.timingFn(delay, fn);
            }, delay);
        },

        /**
         * 生成UUID
         * @returns {string}
         * @constructor
         */
        UUID: function () {
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = (d + Math.random() * 16) % 16 | 0;
                d = Math.floor(d / 16);
                return (c == 'x' ? r : (r & 0x7 | 0x8)).toString(16);
            });
            return uuid;
        },
        /**
         * 根据ID获取值
         * @param id
         * @returns {*}
         */
        getValue: function (id) {
            var itemId = '#' + id;
            return $(itemId).val();
        },
        get: function (id) {
            return $('#' + id);
        },
        setValue: function () {

        },
        resultStatus: function (res, dataGrid) {
            layer.closeAll('loading');
            if (res.code == 200) {
                layer.msg(res.msg, {icon: 1});
                dataGrid.reload();
            } else if (res.code == 1) {
                layer.msg(res.msg, {icon: 0});
                dataGrid.reload();
            } else {
                layer.msg(res.msg, {icon: 2});
                dataGrid.reload();
            }
        },
        alertAndClose: function (res) {
            layer.msg(res.msg, {icon: 1});
            // 设置操作成功的标识
            admin.putLayerData('formOk', true);
            setTimeout(function () {
                // 关闭当前iframe弹窗
                admin.closeThisDialog();
            }, 500);
        }
    };
    exports('emil', emil);
});
