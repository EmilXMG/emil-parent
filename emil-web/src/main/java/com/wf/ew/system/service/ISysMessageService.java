package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.SysMessage;

import java.util.List;

/**
 * 消息中心
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-09
 */
public interface ISysMessageService extends IService<SysMessage> {
    /**
     * 消息中心列表
     *
     * @param page
     * @return
     */
    PageResult<SysMessage> listFull(PageParam page);

    /**
     * 更改消息阅读状态
     *
     * @param rowGuid
     * @param isRead
     * @return
     */
    int changeReadStatus(String rowGuid, String isRead, String userId);

    /**
     * 获取未读消息数量
     *
     * @param userId
     * @return
     */
    int getMessageCount(String userId);

    /**
     * 获取最近消息
     *
     * @param userId
     * @return
     */
    SysMessage getRecentMessage(String userId);

    /**
     * 获取消息列表
     *
     * @param userId
     * @param page
     * @return
     */
    List<SysMessage> getMessageList(String userId, int page, int pageSize);
}
