package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.CodeMain;
import com.wf.ew.system.entity.Subsystem;

import java.util.List;

/**
 * @author emil
 */
public interface CodeMainService extends IService<CodeMain> {
    PageResult<CodeMain> listFull(PageParam page);

    List<CodeMain> getSubsystemName();
}
