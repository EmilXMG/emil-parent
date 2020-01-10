package com.wf.ew.system.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/pages/messageCenter")
public class MessageCenterController {
    @RequiresPermissions("messageCenter:view")
    @RequestMapping()
    public String role() {
        return "system/pages/message_center.html";
    }
}
