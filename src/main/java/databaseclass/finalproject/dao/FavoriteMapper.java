package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.Favorite;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoriteMapper {
    int deleteByPrimaryKey(@Param("username") String username, @Param("resourceid") Integer resourceid);

    int insert(Favorite record);

    Favorite selectByPrimaryKey(@Param("username") String username, @Param("resourceid") Integer resourceid);

    List<Favorite> selectAll();
    
    List<Favorite> selectByUsername(String username);
    
    
}