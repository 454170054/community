package com.yoona.community.dto;

public class QueryDTO {
    private String search;
    private Integer page;
    private Integer size;

    public String getSearch() {
        return search;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
