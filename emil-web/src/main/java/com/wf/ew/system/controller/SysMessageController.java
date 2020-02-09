package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.wf.ew.system.service.ISysMessageService;
import com.wf.ew.system.entity.SysMessage;
import com.wf.ew.common.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import com.wf.ew.common.BaseController;

/**
 * 消息中心前端控制器
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-09
 */
@Controller
@RequestMapping("/system/sysMessage")
public class SysMessageController extends BaseController {
    @Autowired
    private ISysMessageService sysMessageService;

    @RequiresPermissions("sysMessage:view")
    @RequestMapping()
    public String datasourceList() {
        return "system/pages/basic/message/message_center.html";
    }

    @RequiresPermissions("sysMessage:view")
    @RequestMapping("/sysMessageAddPage")
    public String sysMessageAdd() {
        return "system/pages/system/sysMessageAdd.html";
    }

    @RequiresPermissions("sysMessage:view")
    @RequestMapping("/sysMessageEditPage")
    public String sysMessageEdit() {
        return "system/pages/system/sysMessageEdit.html";
    }

    @RequiresPermissions("sysMessage:view")
    @RequestMapping("/sysMessageDetailPage")
    public String sysMessageDetail() {
        return "system/pages/system/sysMessageDetail.html";
    }

    /**
     * 分页查询消息中心
     */
    @RequiresPermissions("sysMessage:view")
    @ResponseBody
    @RequestMapping("/sysMessageList")
    public PageResult<SysMessage> sysMessageList(HttpServletRequest request) {
        PageParam pageParam = new PageParam(request);
        pageParam.put("targetUser",String.valueOf(getLoginUser().getUserId()));
        pageParam.setDefaultOrder(null, new String[]{"operateDate"});
        return sysMessageService.listFull(pageParam);
    }

    /**
     * 添加消息中心
     */
    @RequiresPermissions("sysMessage:update")
    @ResponseBody
    @RequestMapping("/sysMessageAdd")
    public JsonResult sysMessageAdd(SysMessage sysMessage) {
        sysMessage.setOperateDate(new Date());
        sysMessage.setRowGuid(UUID.randomUUID().toString());
        sysMessage.setOperateUserName(getLoginUser().getUsername());
        if (sysMessageService.save(sysMessage)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除消息中心
     */
    @RequiresPermissions("sysMessage:update")
    @ResponseBody
    @RequestMapping("/sysMessageDelete")
    public JsonResult sysMessageDelete(String rowGuid) {
        JSONArray rowGuidArray = JSONArray.parseArray(rowGuid);
        for (Object id : rowGuidArray) {
            sysMessageService.removeById((Serializable) id);
        }
        return JsonResult.ok("删除成功");
    }

    /**
     * 更新消息中心
     */
    @RequiresPermissions("sysMessage:update")
    @ResponseBody
    @RequestMapping("/sysMessageUpdate")
    public JsonResult sysMessageUpdate(SysMessage sysMessage) {
        if (sysMessageService.updateById(sysMessage)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据RowGuid获取消息中心
     */
    @RequiresPermissions("sysMessage:view")
    @ResponseBody
    @RequestMapping("/getSysMessageByRowGuid")
    public SysMessage getSysMessageByRowGuid(SysMessage sysMessage) {
        return sysMessageService.getById(sysMessage.getRowGuid());
    }

    /**
     * 更改消息阅读状态
     */
    @RequiresPermissions("sysMessage:view")
    @ResponseBody
    @RequestMapping("/changeReadStatus")
    public JsonResult changeReadStatus(SysMessage sysMessage) {
        int updateStatus = sysMessageService.changeReadStatus(sysMessage.getRowGuid(), sysMessage.getIsRead(),String.valueOf(getLoginUser().getUserId()));
        if (updateStatus > 0) {
            return JsonResult.ok("操作成功");
        }
        return JsonResult.error("操作失败");
    }

    /**
     * 获取未读消息数量
     */
    @RequiresPermissions("sysMessage:view")
    @ResponseBody
    @RequestMapping("/getMessageCount")
    public String getMessageCount() {
        int messageCount = sysMessageService.getMessageCount(String.valueOf(getLoginUser().getUserId()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageCount", messageCount);
        return jsonObject.toJSONString();
    }
}
