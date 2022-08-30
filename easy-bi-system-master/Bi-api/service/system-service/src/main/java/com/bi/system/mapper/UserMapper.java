package com.bi.system.mapper;

import com.bi.servicebase.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bi.system.entity.vo.UserVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Panda
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-08-21 13:03:59
* @Entity com.bi.servicebase.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT `username` FROM `user`;")
    List<UserVo> getAllUsername();
}




