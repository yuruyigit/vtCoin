package com.block.vtCoin.entity;

/**
 * Created by liubin on 2017/11/8.
 */

public class Page {
    private int PageIndex;
    private int ListNumber;

    public Page(int pageIndex, int listNumber) {
        PageIndex = pageIndex;
        ListNumber = listNumber;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getListNumber() {
        return ListNumber;
    }

    public void setListNumber(int listNumber) {
        ListNumber = listNumber;
    }
}

