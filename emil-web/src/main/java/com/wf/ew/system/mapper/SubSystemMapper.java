package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.Subsystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubSystemMapper extends BaseMapper<Subsystem> {
    List<Subsystem> listFull(@Param("page") PageParam page);

    String getSubsystemNum();

    List<Subsystem> getSubsystemName();

    List<Subsystem> getSubsystemNameAndNum();
}
