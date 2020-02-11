package com.wf.ew.system.service.impl;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.common.expand.generatecode.pojo.GenerateCode;
import com.wf.ew.system.entity.TableBasicinfo;
import com.wf.ew.system.mapper.TableBasicinfoMapper;
import com.wf.ew.system.service.ITableBasicinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author emil
 * @since 2019-08-14
 */
@Service
public class TableBasicinfoServiceImpl extends ServiceImpl<TableBasicinfoMapper, TableBasicinfo> implements ITableBasicinfoService {

    @Override
    public PageResult<TableBasicinfo> listFull(PageParam page) {
        List<TableBasicinfo> tableBasicInfos = baseMapper.listFull(page);
        return new PageResult<>(tableBasicInfos, page.getTotal());
    }

    @Override
    public GenerateCode getDataSourceAndTable(String tableId) {
        return baseMapper.getDataSourceAndTable(tableId);
    }

    @Override
    public PageResult<Map> listTableColumn(PageParam page) {
        List<Map> tableColumnInfos = baseMapper.listTableColumn(page);
        return new PageResult<>(tableColumnInfos, page.getTotal());
    }
}
