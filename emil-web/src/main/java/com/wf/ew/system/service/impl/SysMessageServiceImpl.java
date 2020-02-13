package com.wf.ew.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.mapper.SysMessageMapper;
import com.wf.ew.system.entity.SysMessage;
import com.wf.ew.system.service.ISysMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息中心
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-09
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

    /**
     * 消息中心列表
     *
     * @param page
     * @return
     */
    @Override
    public PageResult<SysMessage> listFull(PageParam page) {
        List<SysMessage> sysMessages = baseMapper.listFull(page);
        return new PageResult<>(sysMessages, page.getTotal());
    }

    /**
     * 更改消息阅读状态
     *
     * @param rowGuid
     * @param isRead
     * @return
     */
    @Override
    public int changeReadStatus(String rowGuid, String isRead, String userId) {
        return baseMapper.changeReadStatus(rowGuid, isRead, userId);
    }

    /**
     * 获取未读消息数量
     *
     * @param userId
     * @return
     */
    @Override
    public int getMessageCount(String userId) {
        return baseMapper.getMessageCount(userId);
    }

    /**
     * 获取最近消息
     *
     * @param userId
     * @return
     */
    @Override
    public SysMessage getRecentMessage(String userId) {
        return baseMapper.getRecentMessage(userId);
    }

    /**
     * 获取消息列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMessage> getMessageList(String userId, int page, int pageSize) {
        return baseMapper.getMessageList(userId, page, pageSize);
    }
}
