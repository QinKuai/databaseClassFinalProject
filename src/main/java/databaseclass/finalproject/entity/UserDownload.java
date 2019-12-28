package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.alibaba.fastjson.annotation.JSONField;

public class UserDownload implements Serializable {
	@Null
    private Integer dId;
	@NotNull
	@JSONField(name = "username")
    private String username;
	@NotNull
    private Integer resourceid;
	@Null
	@JSONField(name = "date")
    private Date dDate;
	@Null
	@JSONField(name = "point")
    private Integer dPoint;
	@Null
	@JSONField(name = "owner")
    private String userUpload;

    private static final long serialVersionUID = 1L;

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getResourceid() {
        return resourceid;
    }

    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public Integer getdPoint() {
        return dPoint;
    }

    public void setdPoint(Integer dPoint) {
        this.dPoint = dPoint;
    }

    public String getUserUpload() {
        return userUpload;
    }

    public void setUserUpload(String userUpload) {
        this.userUpload = userUpload == null ? null : userUpload.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dId=").append(dId);
        sb.append(", userDownload=").append(username);
        sb.append(", resourceid=").append(resourceid);
        sb.append(", dDate=").append(dDate);
        sb.append(", dPoint=").append(dPoint);
        sb.append(", userUpload=").append(userUpload);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}