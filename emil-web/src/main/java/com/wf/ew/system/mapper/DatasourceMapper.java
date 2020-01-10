package com.wf.ew.system.mapper;

import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.Datasource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
public interface DatasourceMapper extends BaseMapper<Datasource> {
    List<Datasource> listFull(@Param("page") PageParam page);

    List<Datasource> getDsNameAndId();
}
