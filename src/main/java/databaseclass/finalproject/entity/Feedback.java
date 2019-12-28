package databaseclass.finalproject.entity;

import java.io.Serializable;

public class Feedback implements Serializable {
    private String feedid;

    private String resourceid;

    private String fdtype;

    private String content;

    private Integer isfinished;

    private String username;

    private static final long serialVersionUID = 1L;

    public String getFeedid() {
        return feedid;
    }

    public void setFeedid(String feedid) {
        this.feedid = feedid == null ? null : feedid.trim();
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid == null ? null : resourceid.trim();
    }

    public String getFdtype() {
        return fdtype;
    }

    public void setFdtype(String fdtype) {
        this.fdtype = fdtype == null ? null : fdtype.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Integer isfinished) {
        this.isfinished = isfinished;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", feedid=").append(feedid);
        sb.append(", resourceid=").append(resourceid);
        sb.append(", fdtype=").append(fdtype);
        sb.append(", content=").append(content);
        sb.append(", isfinished=").append(isfinished);
        sb.append(", username=").append(username);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}