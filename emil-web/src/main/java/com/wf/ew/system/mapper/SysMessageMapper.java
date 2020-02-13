package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.SysMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息中心
 *
 * @author emil
 * @version v1.0
 * @since 2020-02-09
 */
public interface SysMessageMapper extends BaseMapper<SysMessage> {

    /**
     * 消息中心列表
     *
     * @param page
     * @return
     */
    List<SysMessage> listFull(@Param("page") PageParam page);

    /**
     * 更改消息阅读状态
     *
     * @param rowGuid
     * @param isRead
     * @return
     */
    int changeReadStatus(@Param("rowGuid") String rowGuid, @Param("isRead") String isRead, @Param("userId") String userId);

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
    List<SysMessage> getMessageList(@Param("userId") String userId, @Param("page") int page, @Param("pageSize") int pageSize);
}