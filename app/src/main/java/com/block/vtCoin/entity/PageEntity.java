package com.block.vtCoin.entity;

/**
 * Created by liubin on 2017/11/8.
 */

public class PageEntity {
    private int CoinType;
    private Page Paging;

    public PageEntity(int coinType, Page page) {
        CoinType = coinType;
        this.Paging = page;
    }

    public int getCoinType() {
        return CoinType;
    }

    public void setCoinType(int coinType) {
        CoinType = coinType;
    }

    public Page getPage() {
        return Paging;
    }

    public void setPage(Page page) {
        this.Paging = page;
    }
}

