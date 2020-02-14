package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-15
 */
public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {

    /**
     * 部门列表
     *
     * @param page
     * @return
     */
    List<SysDepartment> listFull(@Param("page") PageParam page);
}