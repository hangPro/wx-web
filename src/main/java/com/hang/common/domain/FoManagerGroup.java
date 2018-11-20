package com.hang.common.domain;

import java.util.Date;

public class FoManagerGroup extends Page {
    private Integer id;

    private String gname;

    private String functioninfo;

    private Date ctime;

    private String childtion;

    private String search;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getFunctioninfo() {
        return functioninfo;
    }

    public void setFunctioninfo(String functioninfo) {
        this.functioninfo = functioninfo;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getChildtion() {
        return childtion;
    }

    public void setChildtion(String childtion) {
        this.childtion = childtion;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}