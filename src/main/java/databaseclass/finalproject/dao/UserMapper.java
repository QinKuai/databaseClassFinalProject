package databaseclass.finalproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import databaseclass.finalproject.entity.User;

public interface UserMapper {
	//插入用户
    int insert(User record);
    //通过用户名查找用户
    User selectByUsername(String username);
    //通过邮箱查找用户
    //并保证用户邮箱唯一性
    User selectByEmail(String email);

    List<User> selectAll();
    // 更新用户信息
    int updateUser(User record);
    // 更新用户的积分
    int updatePoint(@Param("username")String username, @Param("point")Integer point);
    // 更新用户的经验值
    int updateExp(@Param("username")String username, @Param("exp")Integer exp);
    // 通过用户名删除用户
    int deleteByUsername(String username);
}