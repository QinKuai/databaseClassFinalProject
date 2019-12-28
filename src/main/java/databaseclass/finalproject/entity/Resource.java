package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.alibaba.fastjson.annotation.JSONField;

public class Resource implements Serializable {

	@Null
    private Integer resourceid;
	
	@NotNull
	private String username;
	
	@NotNull
    private Integer restype;

	@NotNull
    private String url;

    private String picaddr;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Null
    @JSONField(name = "likes")
    private Integer rLike;
    
    @Null
    @JSONField(name = "uploadTime")
    private LocalDateTime rTime;
    
    @NotNull
    private Integer point;


    private static final long serialVersionUID = 1L;


    public Integer getResourceid() {
        return resourceid;
    }


    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }
    
    public void setUsername(String username) {
		this.username = username;
	}
    
    public String getUsername() {
		return username;
	}

 
    public Integer getRestype() {
        return restype;
    }


    public void setRestype(Integer restype) {
        this.restype = restype;
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }


    public String getPicaddr() {
        return picaddr;
    }


    public void setPicaddr(String picaddr) {
        this.picaddr = picaddr == null ? null : picaddr.trim();
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getrLike() {
        return rLike;
    }


    public void setrLike(Integer rLike) {
        this.rLike = rLike;
    }
    
    public void setrTime(LocalDateTime rTime) {
		this.rTime = rTime;
	}
    
    public LocalDateTime getrTime() {
		return rTime;
	}
    
    public void setPoint(Integer point) {
		this.point = point;
	}
    
    public Integer getPoint() {
		return point;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resourceid=").append(resourceid);
        sb.append(", restype=").append(restype);
        sb.append(", url=").append(url);
        sb.append(", picaddr=").append(picaddr);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", rLike=").append(rLike);
        sb.append(", rTime=").append(rTime);
        sb.append(", point=").append(point);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}