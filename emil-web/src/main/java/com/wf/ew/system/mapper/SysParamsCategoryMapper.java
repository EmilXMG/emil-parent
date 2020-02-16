package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysParamsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 系统参数种类

* @author emil
* @since 2020-02-16
* @version v1.0
*/
public interface SysParamsCategoryMapper extends BaseMapper<SysParamsCategory> {

/**
*系统参数种类列表
*
* @param page
* @return
*/
List<SysParamsCategory> listFull(@Param("page") PageParam page);
}