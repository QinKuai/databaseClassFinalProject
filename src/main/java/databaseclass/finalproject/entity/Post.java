package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class Post implements Serializable {
	@Null
    private Integer postid;
	@NotNull
    private String username;
	@NotNull
	@Size(max = 50)
    private String posttitle;
	@Null
    private Integer pLike;
	@Null
    private Integer commentamount;
	@NotNull
    private Integer fieldid;
	@Null
    private Date createDate;
	@NotNull
	private String content;

    private static final long serialVersionUID = 1L;

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

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle == null ? null : posttitle.trim();
    }

    public Integer getpLike() {
        return pLike;
    }

    public void setpLike(Integer pLike) {
        this.pLike = pLike;
    }

    public Integer getCommentamount() {
        return commentamount;
    }

    public void setCommentamount(Integer commentamount) {
        this.commentamount = commentamount;
    }

    public Integer getFieldid() {
        return fieldid;
    }

    public void setFieldid(Integer fieldid) {
        this.fieldid = fieldid;
    }

    public Date getCreatedate() {
        return createDate;
    }

    public void setCreatedate(Date createdate) {
        this.createDate = createdate;
    }
    
    public void setContent(String content) {
		this.content = content;
	}
    
    public String getContent() {
		return content;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postid=").append(postid);
        sb.append(", username=").append(username);
        sb.append(", posttitle=").append(posttitle);
        sb.append(", pLike=").append(pLike);
        sb.append(", commentamount=").append(commentamount);
        sb.append(", fieldid=").append(fieldid);
        sb.append(", createdate=").append(createDate);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}