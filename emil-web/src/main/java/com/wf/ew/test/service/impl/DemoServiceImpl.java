package com.wf.ew.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.test.mapper.DemoMapper;
import com.wf.ew.test.entity.Demo;
import com.wf.ew.test.service.IDemoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 测试
*
* @author emil
* @since 2020-02-10
* @version v1.0
*/
@Service
public class DemoServiceImpl extends ServiceImpl <DemoMapper, Demo> implements IDemoService   {

/**
*测试列表
*
* @param page
* @return
*/
@Override
public PageResult<Demo> listFull(PageParam page) {
List<Demo> demos = baseMapper.listFull(page);
return new PageResult<>(demos, page.getTotal());
}
}
