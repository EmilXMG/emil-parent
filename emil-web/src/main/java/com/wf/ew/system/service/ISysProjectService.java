package com.wf.ew.system.service;

import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysProject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
public interface ISysProjectService extends IService<SysProject> {
    PageResult<SysProject> listFull(PageParam page);

    List<SysProject> getSystemProjectNameAndId();
}
