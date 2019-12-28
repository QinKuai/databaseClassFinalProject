package databaseclass.finalproject.dao;

import databaseclass.finalproject.entity.Post;
import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Integer postid);

    int insert(Post record);

    Post selectByPrimaryKey(Integer postid);

    List<Post> selectAll();

    int updateByPrimaryKey(Post record);
    
    List<Post> selectTop10ByTime();
    
    List<Post> selectTop10ByLike();
    
    List<Post> selectHomePageByTime(Integer fieldid);
    
    List<Post> selectHomePageByLike(Integer fieldid);
    
    List<Post> selectHomePageAllByLike();
    
    List<Post> selectHomePageAllByTime();
    
    List<Post> selectPostByUsername(String username);
    
    int updatePostLike(Integer postid);
    
    int getLastInsertID();
}