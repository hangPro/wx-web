package com.hang.common.domain;

import java.util.Date;
import java.util.List;

public class FoFunction {
    private Integer id;

    private Integer fid;

    private String fname;

    private String furi;

    private Integer sort;

    private Boolean candel;

    private Boolean state;

    private Date ctime;

    private String fidname;

    private Integer childfocount;

    private List<FoFunction> clist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public String getFuri() {
        return furi;
    }

    public void setFuri(String furi) {
        this.furi = furi == null ? null : furi.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getCandel() {
        return candel;
    }

    public void setCandel(Boolean candel) {
        this.candel = candel;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getFidname() {
        return fidname;
    }

    public void setFidname(String fidname) {
        this.fidname = fidname;
    }

    public Integer getChildfocount() {
        return childfocount;
    }

    public void setChildfocount(Integer childfocount) {
        this.childfocount = childfocount;
    }

    public List<FoFunction> getClist() {
        return clist;
    }

    public void setClist(List<FoFunction> clist) {
        this.clist = clist;
    }

    @Override
    public String toString() {
        return "FoFunction{" +
                "id=" + id +
                ", fid=" + fid +
                ", fname='" + fname + '\'' +
                ", furi='" + furi + '\'' +
                ", sort=" + sort +
                ", candel=" + candel +
                ", state=" + state +
                ", ctime=" + ctime +
                ", fidname='" + fidname + '\'' +
                ", childfocount=" + childfocount +
                ", clist=" + clist +
                '}';
    }
}