package com.wf.ew.system.mapper;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.pojo.GenerateCode;
import com.wf.ew.system.entity.TableBasicinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author emil
 * @since 2019-08-14
 */
public interface TableBasicinfoMapper extends BaseMapper<TableBasicinfo> {
    List<TableBasicinfo> listFull(@Param("page") PageParam page);

    GenerateCode getDataSourceAndTable(@Param("tableId") String tableId);


//    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
//    List<Map> listTable();

    List<Map> listTableColumn(@Param("page") PageParam page);

}
