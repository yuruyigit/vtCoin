package com.block.vtCoin.entity;

import java.util.List;

/**
 * @Description
 * @Author lijie
 * @Date 2017/7/14.
 */
public class HandleOrderEntity extends BaseEntity {
    private List<?> data;
    public List<?> getData() {
        return data;
    }
    public void setData(List<?> data) {
        this.data = data;
    }
}
