package com.tbj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tbj.entity.SysUser;
import com.tbj.mapper.SysUserMapper;
import com.tbj.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> listUser() {
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        return sysUserMapper.selectList(qw);

    }
}
