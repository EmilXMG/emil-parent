package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.CodeItemMapper;
import com.wf.ew.system.entity.CodeItem;
import com.wf.ew.system.service.CodeItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeItemServiceImpl extends ServiceImpl<CodeItemMapper, CodeItem> implements CodeItemService {

    @Override
    public PageResult<CodeItem> listFull(PageParam page) {
        List<CodeItem> codeItems = baseMapper.listFull(page);
        return new PageResult<>(codeItems, page.getTotal());
    }
}
