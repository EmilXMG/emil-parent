package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.SysParamsMapper;
import com.wf.ew.system.entity.SysParams;
import com.wf.ew.system.service.ISysParamsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统参数
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-08
 */
@Service
public class SysParamsServiceImpl extends ServiceImpl<SysParamsMapper, SysParams> implements ISysParamsService {

    /**
     * 系统参数列表
     *
     * @param page
     * @return
     */
    @Override
    public PageResult<SysParams> listFull(PageParam page) {
        List<SysParams> sysParamss = baseMapper.listFull(page);
        return new PageResult<>(sysParamss, page.getTotal());
    }

    /**
     * 根据参数名获取参数值
     *
     * @param paramName
     * @return
     */
    @Override
    public String getParamValue(String paramName) {
        return baseMapper.getParamValue(paramName);
    }
}
