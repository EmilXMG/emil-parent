package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.CodeMainMapper;
import com.wf.ew.system.entity.CodeMain;
import com.wf.ew.system.service.CodeMainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author emil
 */
@Service
public class CodeMainServiceImpl extends ServiceImpl<CodeMainMapper, CodeMain> implements CodeMainService {
    @Override
    public PageResult<CodeMain> listFull(PageParam page) {
        List<CodeMain> codeMains = baseMapper.listFull(page);
        return new PageResult<>(codeMains, page.getTotal());
    }

    @Override
    public List<CodeMain> getSubsystemName() {
        return baseMapper.getCodeMainName();
    }
}
