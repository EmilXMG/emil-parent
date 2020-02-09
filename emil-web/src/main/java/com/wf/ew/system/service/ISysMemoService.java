package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysMemo;

import java.util.List;

/**
 * 便签信息
 *
 * @author emil
 * @version v1.0
 * @since 2019-09-14
 */
public interface ISysMemoService extends IService<SysMemo> {
    /**
     * 便签信息列表
     *
     * @param page
     * @return
     */
    PageResult<SysMemo> listFull(PageParam page);

    /**
     * 根据用户ID获取便签信息
     *
     * @param loginUserId
     * @return
     */
    List<SysMemo> getSysMemoByUserId(Integer loginUserId);
}
