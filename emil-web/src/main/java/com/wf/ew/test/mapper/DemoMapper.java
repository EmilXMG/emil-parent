package com.wf.ew.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.test.entity.Demo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 测试

* @author emil
* @since 2020-02-10
* @version v1.0
*/
public interface DemoMapper extends BaseMapper<Demo> {

/**
*测试列表
*
* @param page
* @return
*/
List<Demo> listFull(@Param("page") PageParam page);
}