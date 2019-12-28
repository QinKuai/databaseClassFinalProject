package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.Field;
import java.util.List;

public interface FieldMapper {
    int deleteByPrimaryKey(Integer fieldid);

    int insert(Field record);

    Field selectByPrimaryKey(Integer fieldid);

    List<Field> selectAll();

    int updateByPrimaryKey(Field record);
    
    int selectAmountByFieldid(Integer fieldid);
}