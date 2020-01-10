package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.SysMemoMapper;
import com.wf.ew.system.entity.SysMemo;
import com.wf.ew.system.service.ISysMemoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 便签信息
 *
 * @author emil
 * @version v1.0
 * @since 2019-09-14
 */
@Service
public class SysMemoServiceImpl extends ServiceImpl<SysMemoMapper, SysMemo> implements ISysMemoService {

    /**
     * 便签信息列表
     *
     * @param page
     * @return
     */
    @Override
    public PageResult<SysMemo> listFull(PageParam page) {
        List<SysMemo> sysMemos = baseMapper.listFull(page);
        return new PageResult<>(sysMemos, page.getTotal());
    }

    /**
     * 根据用户ID获取便签信息
     *
     * @param loginUserId
     * @return
     */
    @Override
    public List<SysMemo> getSysMemoByUserId(Integer loginUserId) {
        return baseMapper.getSysMemoByUserId(loginUserId);
    }
}
