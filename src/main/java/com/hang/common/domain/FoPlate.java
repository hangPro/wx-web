package com.hang.common.domain;

import java.util.Date;
import java.util.List;

public class FoPlate extends Page {
    private Integer id;

    private String name;

    private String description;

    private String imgurl;

    private Integer sort;

    private Date ctime;

    private String search;

    private List<FoPlatePage> pageList;

    public List<FoPlatePage> getPageList() {
        return pageList;
    }

    public void setPageList(List<FoPlatePage> pageList) {
        this.pageList = pageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}