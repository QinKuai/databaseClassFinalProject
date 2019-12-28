package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.IncomeRecord;
import java.util.List;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Integer inId);

    int insert(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer inId);

    List<IncomeRecord> selectAll();

    int updateByPrimaryKey(IncomeRecord record);
    
    List<IncomeRecord> selectByUsername(String username);
}