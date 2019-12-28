package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.Resource;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    
    int deleteByPrimaryKey(Integer resourceid);
    
    int insert(Resource record);
    
    Resource selectByPrimaryKey(Integer resourceid);
    
    List<Resource> selectAll();
    
    int updateByPrimaryKey(Resource record);
    
    List<Resource> selectByOrder(@Param("fieldid")Integer fieldid, @Param("limit")Integer limit);
    
    List<Resource> selectByUsername(String username);
    
    List<Resource> selectByUsernameWithLimit(@Param("username") String username, @Param("limit")Integer limit);
    
    List<Resource> selectByType(Integer resourceType);
    
    int selectUploadCount(String username);
    
    int selectAmount();
}