package databaseclass.finalproject.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class UserRank implements Serializable {
	@JSONField(name = "rank")
    private Short uRank;

    private Integer search;

    private Integer seeurl;

    private Integer upload;

    private Integer post;

    private Integer uComment;

    private Integer newfield;
    @JSONField(name = "exp")
    private Integer upExp;

    private static final long serialVersionUID = 1L;

    public Short getuRank() {
        return uRank;
    }

    public void setuRank(Short uRank) {
        this.uRank = uRank;
    }

    public Integer getSearch() {
        return search;
    }

    public void setSearch(Integer search) {
        this.search = search;
    }

    public Integer getSeeurl() {
        return seeurl;
    }

    public void setSeeurl(Integer seeurl) {
        this.seeurl = seeurl;
    }

    public Integer getUpload() {
        return upload;
    }

    public void setUpload(Integer upload) {
        this.upload = upload;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getuComment() {
        return uComment;
    }

    public void setuComment(Integer uComment) {
        this.uComment = uComment;
    }

    public Integer getNewfield() {
        return newfield;
    }

    public void setNewfield(Integer newfield) {
        this.newfield = newfield;
    }

    public Integer getUpExp() {
        return upExp;
    }

    public void setUpExp(Integer upExp) {
        this.upExp = upExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uRank=").append(uRank);
        sb.append(", search=").append(search);
        sb.append(", seeurl=").append(seeurl);
        sb.append(", upload=").append(upload);
        sb.append(", post=").append(post);
        sb.append(", uComment=").append(uComment);
        sb.append(", newfield=").append(newfield);
        sb.append(", upExp=").append(upExp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}