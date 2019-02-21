package com.micolor.commoncore.datatables.domain;

/**
 * FileName:com.micolor.common.entities.Search.
 * Discription: TODO 填写类描述
 * Author: geyupeng.
 * DateTime 16/3/23.
 */
public class Search {
    private String value;
    private boolean regex;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public boolean isRegex() {
        return regex;
    }
    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "Search [value=" + value + ", regex=" + regex + "]";
    }
}
