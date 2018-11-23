package com.hang.common.domain;

import java.util.Date;

public class FoInfo {
    private Integer id;

    private Integer groupcnt;

    private String description;

    private String inviteDesc;

    private String addDesc;

    private String imgurl;

    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupcnt() {
        return groupcnt;
    }

    public void setGroupcnt(Integer groupcnt) {
        this.groupcnt = groupcnt;
    }

    public String getAddDesc() {
        return addDesc;
    }

    public void setAddDesc(String addDesc) {
        this.addDesc = addDesc;
    }

    public String getInviteDesc() {
        return inviteDesc;
    }

    public void setInviteDesc(String inviteDesc) {
        this.inviteDesc = inviteDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}