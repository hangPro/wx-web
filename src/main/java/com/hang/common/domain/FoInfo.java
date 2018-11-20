package com.hang.common.domain;

import java.util.Date;

public class FoInfo {
    private Integer id;

    private Integer groupcnt;

    private String description;

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