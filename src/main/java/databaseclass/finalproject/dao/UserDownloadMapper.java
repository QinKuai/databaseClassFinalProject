package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.UserDownload;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserDownloadMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(UserDownload record);

    UserDownload selectByPrimaryKey(Integer dId);

    List<UserDownload> selectAll();

    int updateByPrimaryKey(UserDownload record);
    
    UserDownload selectByUsernameAndResourceid(@Param("username") String username, @Param("resourceid") Integer resourceid);
    
    List<UserDownload> selectDownloadByUsername(String username);
    
    List<UserDownload> selectDownloadedByUsername(String username);
}