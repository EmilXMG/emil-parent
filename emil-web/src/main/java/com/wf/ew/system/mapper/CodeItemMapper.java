package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.CodeItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeItemMapper extends BaseMapper<CodeItem> {
    List<CodeItem> listFull(@Param("page") PageParam page);
}
