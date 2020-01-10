package com.wf.ew.system.service.impl;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.Datasource;
import com.wf.ew.system.mapper.DatasourceMapper;
import com.wf.ew.system.service.IDatasourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
@Service
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, Datasource> implements IDatasourceService {

    @Override
    public PageResult<Datasource> listFull(PageParam page) {
        List<Datasource> datasources = baseMapper.listFull(page);
        return new PageResult<>(datasources, page.getTotal());
    }

    @Override
    public List<Datasource> getDsNameAndId() {
        return baseMapper.getDsNameAndId();
    }
}
