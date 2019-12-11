package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.User;

public interface UserMapper {
	//插入用户
    int insert(User record);
    //通过用户名查找用户
    User selectByUsername(String username);
    //通过邮箱查找用户
    //并保证用户邮箱唯一性
    User selectByEmail(String email);

    //List<User> selectAll();
    //更新用户信息
    int updateByUsername(User record);
    //通过用户名删除用户
    int deleteByUsername(String username);
}