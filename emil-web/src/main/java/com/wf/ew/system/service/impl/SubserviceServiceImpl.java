package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.SubSystemMapper;
import com.wf.ew.system.entity.Subsystem;
import com.wf.ew.system.service.SubsystemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubserviceServiceImpl extends ServiceImpl<SubSystemMapper, Subsystem> implements SubsystemService {

    @Override
    public PageResult<Subsystem> listFull(PageParam page) {
        List<Subsystem> subsystems = baseMapper.listFull(page);
        return new PageResult<>(subsystems, page.getTotal());
    }

    @Override
    public String getSubsystemNum() {
        return baseMapper.getSubsystemNum();
    }

    @Override
    public List<Subsystem> getSubsystemName() {
        return baseMapper.getSubsystemName();
    }

    @Override
    public List<Subsystem> getSubsystemNameAndNum() {
        return baseMapper.getSubsystemNameAndNum();
    }
}
