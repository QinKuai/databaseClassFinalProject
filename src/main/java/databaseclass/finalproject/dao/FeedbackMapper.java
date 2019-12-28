package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.Feedback;
import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer feedid);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer feedid);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);
}