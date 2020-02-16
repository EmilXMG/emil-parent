package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysParamsCategory;

/**
* 系统参数种类

* @author emil
* @since 2020-02-16
* @version v1.0
*/
public interface ISysParamsCategoryService extends IService<SysParamsCategory> {
/**
*系统参数种类列表
*
* @param page
* @return
*/
PageResult<SysParamsCategory> listFull(PageParam page);
}
