package com.bi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bi.servicebase.entity.User;
import com.bi.system.entity.vo.UserVo;
import com.bi.system.service.UserService;
import com.bi.system.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Panda
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-08-21 13:03:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    QueryWrapper<User> queryWrapper;

    @Override
    public User login(User login) {
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",login.getUsername())
                .eq("password",login.getPassword());
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UserVo> getAllUsername() {
        return baseMapper.getAllUsername();
    }
}




