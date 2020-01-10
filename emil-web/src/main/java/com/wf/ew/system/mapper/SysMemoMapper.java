package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysMemo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 便签信息
 *
 * @author emil
 * @version v1.0
 * @since 2019-09-14
 */
public interface SysMemoMapper extends BaseMapper<SysMemo> {

    /**
     * 便签信息列表
     *
     * @param page
     * @return
     */
    List<SysMemo> listFull(@Param("page") PageParam page);

    /**
     * 根据用户ID获取便签信息
     *
     * @param loginUserId
     * @return
     */
    List<SysMemo> getSysMemoByUserId(Integer loginUserId);
}