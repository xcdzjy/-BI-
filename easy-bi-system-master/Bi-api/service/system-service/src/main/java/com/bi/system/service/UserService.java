package com.bi.system.service;

import com.bi.servicebase.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bi.system.entity.vo.UserVo;

import java.util.List;

/**
* @author Panda
* @description 针对表【user】的数据库操作Service
* @createDate 2022-08-21 13:03:59
*/
public interface UserService extends IService<User> {

    User login(User login);

    List<UserVo> getAllUsername();
}
