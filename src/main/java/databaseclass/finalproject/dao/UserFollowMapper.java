package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.UserFollow;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFollowMapper {
    int deleteByPrimaryKey(@Param("follower") String follower, @Param("following") String following);

    int insert(UserFollow record);

    UserFollow selectByPrimaryKey(@Param("follower") String follower, @Param("following") String following);

    List<UserFollow> selectAll();

    int updateByPrimaryKey(UserFollow record);
    
    // 获取被关注列表
    List<UserFollow> selectFollower(String username);
    // 获取关注列表
    List<UserFollow> selectFollowing(String username);
    // 获取被关注数量
    int selectFollowerCount(String username);
    // 获取关注数量
    int selectFollowingCount(String username);
}