package com.block.vtCoin.entity1;

import com.block.vtCoin.entity1.BaseEntity;

/**
 * @param <T> 泛型在类里面的使用
 */
public class HttpResult<T> extends BaseEntity {
    private int msgNo;
    private String msg;
    private  T data;

    public int getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(int msgNo) {
        this.msgNo = msgNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
