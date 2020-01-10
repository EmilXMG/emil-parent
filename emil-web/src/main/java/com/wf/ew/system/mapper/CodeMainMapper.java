package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.CodeMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author emil
 */
public interface CodeMainMapper extends BaseMapper<CodeMain> {

    List<CodeMain> listFull(@Param("page") PageParam page);

    List<CodeMain> getCodeMainName();
}
