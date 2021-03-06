package com.wf.ew.system.controller;


import com.wf.ew.common.JsonResult;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.utils.StringUtil;
import com.wf.ew.system.entity.CodeItem;
import com.wf.ew.system.entity.CodeMain;
import com.wf.ew.system.service.CodeItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author emil
 */
@Controller
@RequestMapping("/system/codeItem")
public class CodeItemController {
    private final CodeItemService codeItemService;

    public CodeItemController(CodeItemService codeItemService) {
        this.codeItemService = codeItemService;
    }

    @RequestMapping()
    public String codeMain() {
        return "system/pages/basic/data_manager/codeItem.html";
    }

    List<CodeItem> codeItemList;

    /**
     * 添加代码子项
     **/
    @RequiresPermissions("codeMain:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(CodeItem codeItem) {
        if (StringUtil.isBlank(codeItem.getItemText())) {
            return JsonResult.warning("请输入代码项文本!");
        }
        if (StringUtil.isBlank(codeItem.getItemValue())) {
            return JsonResult.warning("请输入代码项值!");
        }
        codeItemList = codeItemService.getCodeItemByMainId(codeItem.getCodeId());
        if (codeItemList.size() > 0 && codeItemList != null) {
            for (CodeItem item : codeItemList) {
                if (codeItem.getItemValue().equals(item.getItemValue())) {
                    return JsonResult.warning("代码项值不能重复!");
                }
            }
        }
        codeItem.setCreateDate(new Date());
        if (codeItemService.save(codeItem)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 列出代码子项
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<CodeItem> list(HttpServletRequest request) {
        return codeItemService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"create_date"}));
    }

    /**
     * 删除代码子项
     */
    @RequiresPermissions("codeMain:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer itemId) {
        if (codeItemService.removeById(itemId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 更新代码项子项
     */
    @RequiresPermissions("codeMain:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(CodeItem codeItem) {
        if (StringUtil.isNotBlank(codeItem.getItemValue())) {
            codeItemList = codeItemService.getCodeItemByMainId(codeItem.getCodeId());
            if (codeItemList.size() > 0 && codeItemList != null) {
                for (CodeItem item : codeItemList) {
                    if (codeItem.getItemValue().equals(item.getItemValue())) {
                        return JsonResult.warning("代码项值不能重复!");
                    }
                }
            }
        }
        if (codeItemService.updateById(codeItem)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
