package com.wf.ew.system.service.impl;

import com.wf.ew.system.entity.Authorities;
import com.wf.ew.system.mapper.AuthoritiesMapper;
import com.wf.ew.system.service.AuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wangfan
 * @since 2019-02-11
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements AuthoritiesService {

    @Override
    public List<Authorities> listByUserId(Integer userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public List<Authorities> listByRoleId(Integer roleId) {
        if (roleId == null) {
            return new ArrayList<>();
        }
        return baseMapper.listByRoleId(roleId);
    }

    /**
     * 获取权限树
     *
     * @return
     */
    @Override
    public List<Authorities> getAuthorities() {
        return baseMapper.getAuthorities();
    }


    @Override
    public List<Authorities> listByRoleIds(List<Integer> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return new ArrayList<>();
        }
        return baseMapper.listByRoleIds(roleIds);
    }

}
