package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Comment implements Serializable {
	@Null
    private Integer commentid;
	@NotNull
    private Integer postid;
	@NotNull
    private String username;
	@NotNull
    private String content;
	@Null
    private Integer pLike;
    @Null
    private LocalDateTime date;

    private static final long serialVersionUID = 1L;

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getpLike() {
        return pLike;
    }

    public void setpLike(Integer pLike) {
        this.pLike = pLike;
    }
    
    public void setDate(LocalDateTime date) {
		this.date = date;
	}
    
    public LocalDateTime getDate() {
		return date;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentid=").append(commentid);
        sb.append(", postid=").append(postid);
        sb.append(", username=").append(username);
        sb.append(", content=").append(content);
        sb.append(", pLike=").append(pLike);
        sb.append(", date=").append(date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}