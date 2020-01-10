package com.wf.ew.system.service;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.pojo.GenerateCode;
import com.wf.ew.system.entity.TableBasicinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author emil
 * @since 2019-08-14
 */
public interface ITableBasicinfoService extends IService<TableBasicinfo> {
    PageResult<TableBasicinfo> listFull(PageParam page);

    GenerateCode getDataSourceAndTable(String tableId);

    PageResult<Map> listTableColumn(PageParam tableName);
}
