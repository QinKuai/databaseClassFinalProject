package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.UserRecord;
import java.util.List;

public interface UserRecordMapper {
    int deleteByPrimaryKey(String rId);

    int insert(UserRecord record);

    UserRecord selectByPrimaryKey(String rId);

    List<UserRecord> selectAll();

    int updateByPrimaryKey(UserRecord record);
}