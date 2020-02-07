package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysParams;

/**
* 系统参数

* @author emil
* @since 2020-02-08
* @version v1.0
*/
public interface ISysParamsService extends IService<SysParams> {
/**
*系统参数列表
*
* @param page
* @return
*/
PageResult<SysParams> listFull(PageParam page);
}
