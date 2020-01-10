package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.wf.ew.common.BaseController;
import com.wf.ew.common.JsonResult;
import com.wf.ew.system.entity.User;
import com.wf.ew.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author emil
 */
@Controller
@RequestMapping("/tpl/userCenter")
public class UserCenterController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     */
    @ResponseBody
    @RequestMapping("/getUserById")
    public String getSysMemoByUserId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", userService.getById(getLoginUserId()));
        return jsonObject.toJSONString();
    }

    /**
     * 更新用户信息
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public JsonResult update(User user) {
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
