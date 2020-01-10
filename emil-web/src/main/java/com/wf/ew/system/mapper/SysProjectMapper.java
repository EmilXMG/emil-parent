package com.wf.ew.system.mapper;

import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
public interface SysProjectMapper extends BaseMapper<SysProject> {
    List<SysProject> listFull(@Param("page") PageParam page);

    List<SysProject> getSystemProjectNameAndId();
}
