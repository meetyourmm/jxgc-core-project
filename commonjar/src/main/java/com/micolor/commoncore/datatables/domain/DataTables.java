package com.micolor.commoncore.datatables.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName:com.micolor.common.entities.DataTables.
 * Discription: TODO 填写类描述
 * Author: geyupeng.
 * DateTime 16/3/23.
 */

public class DataTables {
    //请求传递参数
    private int draw = 0;
    private List<Columns> columns = new ArrayList<Columns>();
    private List<Order> order = new ArrayList<Order>();
    private int start;
    private int length;
    private Search search = new Search();

    //相应传递参数
    private Object data;
    private int recordsTotal = 0;
    private int recordsFiltered = 0;

    public int getDraw() {
        return draw;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }
    public List<Columns> getColumns() {
        return columns;
    }
    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }
    public List<Order> getOrder() {
        return order;
    }
    public void setOrder(List<Order> order) {
        this.order = order;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public Search getSearch() {
        return search;
    }
    public void setSearch(Search search) {
        this.search = search;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public int getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public int getRecordsFiltered() {
        return recordsFiltered;
    }
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
    public int  getcurrPage(){
        int currPage = (this.getStart() % this.getLength() == 0 ? this.getStart() /  this.getLength()  : this.getStart() /  this.getLength()  + 1)+1;   //第几页
        return currPage;
    }
    public String toString() {
        String columnsString = "";
        if(columns!= null && columns.size()>0){
            columnsString =columns.get(0).toString();
        }

        String orderString = "";
        if(order!= null && order.size()>0){
            orderString =order.get(0).toString();
        }

        String datatable2String ="DataTables [draw=" + draw + ", columns=" + columnsString+ ", order=" + orderString + ", start=" + start
                + ", length=" + length + ", search=" + search + ", data=" + data + ", recordsTotal=" + recordsTotal
                + ", recordsFiltered=" + recordsFiltered + "]";
        return datatable2String;
    }
}