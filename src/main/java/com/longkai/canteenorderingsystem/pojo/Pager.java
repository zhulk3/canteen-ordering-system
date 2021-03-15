package com.longkai.canteenorderingsystem.pojo;

public class Pager {
    private int curPage;
    private int perPageRows;
    private int rowCount;
    private int pageCount;

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPerPageRows(int perPageRows) {
        this.perPageRows = perPageRows;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getPerPageRows() {
        return perPageRows;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getPageCount() {
        return (this.rowCount + perPageRows - 1) / perPageRows;
    }

    public int getFirstLimitParam() {
        return (this.curPage - 1) * this.perPageRows;
    }
}

