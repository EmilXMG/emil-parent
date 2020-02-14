package com.wf.ew.system.mapper;

import com.wf.ew.system.entity.Authorities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author wangfan
 * @since 2019-02-11
 */
public interface AuthoritiesMapper extends BaseMapper<Authorities> {

    List<Authorities> listByUserId(Integer userId);

    List<Authorities> listByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Authorities> listByRoleId(Integer roleId);

    /**
     * 获取权限树
     *
     * @return
     */
    List<Authorities> getAuthorities();
}
