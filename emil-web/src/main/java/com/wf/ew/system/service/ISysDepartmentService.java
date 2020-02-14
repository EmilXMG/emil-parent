package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysDepartment;

/**
 * 部门
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-15
 */
public interface ISysDepartmentService extends IService<SysDepartment> {
    /**
     * 部门列表
     *
     * @param page
     * @return
     */
    PageResult<SysDepartment> listFull(PageParam page);
}
