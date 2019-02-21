package com.micolor.commoncore.datatables.domain;

/**
 * FileName:com.micolor.common.entities.Columns.
 * Discription: 用于DataTable的参数对象
 * Author: geyupeng.
 * DateTime 16/3/23.
 */
public class Columns {

    private String data;
    private String name;
    private boolean searchable;
    private boolean orderable;
    private Search search;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSearchable() {
        return searchable;
    }
    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }
    public boolean isOrderable() {
        return orderable;
    }
    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }
    public Search getSearch() {
        return search;
    }
    public void setSearch(Search search) {
        this.search = search;
    }
    @Override
    public String toString() {
        return "Columns [data=" + data + ", name=" + name + ", searchable=" + searchable + ", orderable=" + orderable
                + ", search=" + search.toString() + "]";
    }
}
