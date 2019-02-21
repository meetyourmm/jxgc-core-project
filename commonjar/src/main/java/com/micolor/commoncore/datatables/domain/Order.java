package com.micolor.commoncore.datatables.domain;

/**
 * FileName:com.micolor.common.entities.Order.
 * Discription: TODO 填写类描述
 * Author: geyupeng.
 * DateTime 16/3/23.
 */
public class Order {
    private int column;
    private String dir;
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public String getDir() {
        return dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Order [column=" + column + ", dir=" + dir + "]";
    }
}
