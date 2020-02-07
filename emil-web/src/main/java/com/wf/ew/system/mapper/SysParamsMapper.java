package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 系统参数

* @author emil
* @since 2020-02-08
* @version v1.0
*/
public interface SysParamsMapper extends BaseMapper<SysParams> {

/**
*系统参数列表
*
* @param page
* @return
*/
List<SysParams> listFull(@Param("page") PageParam page);
}