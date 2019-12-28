package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.util.Date;

public class IncomeRecord implements Serializable {
    private Integer inId;

    private String username;

    private Float fee;

    private String teetype;

    private Date inDate;

    private static final long serialVersionUID = 1L;

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public String getTeetype() {
        return teetype;
    }

    public void setTeetype(String teetype) {
        this.teetype = teetype == null ? null : teetype.trim();
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inId=").append(inId);
        sb.append(", username=").append(username);
        sb.append(", fee=").append(fee);
        sb.append(", teetype=").append(teetype);
        sb.append(", inDate=").append(inDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}