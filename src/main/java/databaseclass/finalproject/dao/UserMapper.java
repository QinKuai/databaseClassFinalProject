package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.Insert;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    //int deleteByPrimaryKey(String user);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    @Insert("insert into user(username, u_password, u_registertime, u_email, u_type) "
    		+ "values(#{username,jdbcType=VARCHAR},#{uPassword,jdbcType=VARCHAR}, #{uRegistertime,jdbcType=DATE}, #{uEmail,jdbcType=VARCHAR}, #{uType,jdbcType=VARCHAR})")
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    //User selectByPrimaryKey(String user);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    //int updateByPrimaryKey(User record);
}