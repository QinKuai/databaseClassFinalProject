package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Favorite implements Serializable {
	@NotNull
    private String username;

	@NotNull
    private Integer resourceid;

	@Null
    private Date favoDate;

	@Null
    private Date cancelDate;

    private static final long serialVersionUID = 1L;

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

    public Date getFavoDate() {
        return favoDate;
    }

    public void setFavoDate(Date favoDate) {
        this.favoDate = favoDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", username=").append(username);
        sb.append(", resourceid=").append(resourceid);
        sb.append(", favoDate=").append(favoDate);
        sb.append(", cancelDate=").append(cancelDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}