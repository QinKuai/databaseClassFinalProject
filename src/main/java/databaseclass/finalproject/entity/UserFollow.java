package databaseclass.finalproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class UserFollow implements Serializable {
	@NotNull
    private String follower;
	@NotNull
    private String following;
	@Null
    private Date followDate;
	@Null
    private Date cancelDate;

    private static final long serialVersionUID = 1L;

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower == null ? null : follower.trim();
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following == null ? null : following.trim();
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
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
        sb.append(", follower=").append(follower);
        sb.append(", following=").append(following);
        sb.append(", followDate=").append(followDate);
        sb.append(", cancelDate=").append(cancelDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}