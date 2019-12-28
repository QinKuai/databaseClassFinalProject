package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentid);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer commentid);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
    
    List<Comment> selectAllByPostid(Integer postid);
    
    int updateCommentLikes(Integer commentid);
}