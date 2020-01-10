package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.CodeItem;


public interface CodeItemService extends IService<CodeItem> {

    PageResult<CodeItem> listFull(PageParam page);
}
