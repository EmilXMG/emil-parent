package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.SysDepartmentMapper;
import com.wf.ew.system.entity.SysDepartment;
import com.wf.ew.system.service.ISysDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-15
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements ISysDepartmentService {

    /**
     * 部门列表
     *
     * @param page
     * @return
     */
    @Override
    public PageResult<SysDepartment> listFull(PageParam page) {
        List<SysDepartment> sysDepartments = baseMapper.listFull(page);
        return new PageResult<>(sysDepartments, page.getTotal());
    }
}
