package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.util.Date;

public class Field implements Serializable {
    private Integer fieldid;

    private String description;

    private Date foundate;

    private Integer resamount;

    private static final long serialVersionUID = 1L;

    public Integer getFieldid() {
        return fieldid;
    }

    public void setFieldid(Integer fieldid) {
        this.fieldid = fieldid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getFoundate() {
        return foundate;
    }

    public void setFoundate(Date foundate) {
        this.foundate = foundate;
    }

    public Integer getResamount() {
        return resamount;
    }

    public void setResamount(Integer resamount) {
        this.resamount = resamount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fieldid=").append(fieldid);
        sb.append(", description=").append(description);
        sb.append(", foundate=").append(foundate);
        sb.append(", resamount=").append(resamount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}