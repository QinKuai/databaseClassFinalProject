package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.UserRank;
import java.util.List;

public interface UserRankMapper {
    int deleteByPrimaryKey(Short uRank);

    int insert(UserRank record);

    UserRank selectByPrimaryKey(Short uRank);

    List<UserRank> selectAll();

    int updateByPrimaryKey(UserRank record);
    
    List<UserRank> selectAllExp();
}