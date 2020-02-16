package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.SysParamsCategoryMapper;
import com.wf.ew.system.entity.SysParamsCategory;
import com.wf.ew.system.service.ISysParamsCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 系统参数种类
*
* @author emil
* @since 2020-02-16
* @version v1.0
*/
@Service
public class SysParamsCategoryServiceImpl extends ServiceImpl <SysParamsCategoryMapper, SysParamsCategory> implements ISysParamsCategoryService   {

/**
*系统参数种类列表
*
* @param page
* @return
*/
@Override
public PageResult<SysParamsCategory> listFull(PageParam page) {
List<SysParamsCategory> sysParamsCategorys = baseMapper.listFull(page);
return new PageResult<>(sysParamsCategorys, page.getTotal());
}
}
