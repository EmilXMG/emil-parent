package com.wf.ew.system.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author emil
 */
@Controller
@RequestMapping("/system/messageCenter")
public class MessageCenterController {
    @RequiresPermissions("messageCenter:view")
    @RequestMapping()
    public String messageCenter() {
        return "system/pages/basic/message/message_center.html";
    }
}
