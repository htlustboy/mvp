package com.mvp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mvp.model.User;

@Repository
public interface UserMapper {
	
	//根据用户名查找用户（登陆认证）
	User findUserByUserName(@Param("username")String username);

}
