package com.wf.ew.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.test.entity.Demo;

/**
* 测试

* @author emil
* @since 2020-02-10
* @version v1.0
*/
public interface IDemoService extends IService<Demo> {
/**
*测试列表
*
* @param page
* @return
*/
PageResult<Demo> listFull(PageParam page);
}
