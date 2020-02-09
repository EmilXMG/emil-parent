package com.wf.ew.system.service.impl;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysProject;
import com.wf.ew.system.mapper.SysProjectMapper;
import com.wf.ew.system.service.ISysProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
@Service
public class SysProjectServiceImpl extends ServiceImpl<SysProjectMapper, SysProject> implements ISysProjectService {

    @Override
    public PageResult<SysProject> listFull(PageParam page) {
        List<SysProject> sysProjects = baseMapper.listFull(page);
        return new PageResult<>(sysProjects, page.getTotal());
    }

    @Override
    public List<SysProject> getSystemProjectNameAndId() {
        return baseMapper.getSystemProjectNameAndId();
    }
}
