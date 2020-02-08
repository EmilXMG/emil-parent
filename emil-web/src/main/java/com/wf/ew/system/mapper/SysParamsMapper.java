package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统参数
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-08
 */
public interface SysParamsMapper extends BaseMapper<SysParams> {

    /**
     * 系统参数列表
     *
     * @param page
     * @return
     */
    List<SysParams> listFull(@Param("page") PageParam page);

    /**
     * 根据参数名获取参数值
     *
     * @param paramName
     * @return
     */
    String getParamValue(String paramName);
}