package com.block.vtCoin.market.v;

import com.block.net.mvp.view.ILoadingView;

import java.util.List;
import java.util.Map;

/**
 * @Description view 层，传一个实体类的对象进来
 */
public interface IListView<E> extends ILoadingView {
    /**
     * 请求的参数
     * @return map
     */
    Map<String,Object> getParameters();

    /**
     * 请求错误
     * @param code 错误码
     * @param msg 错误信息
     */
    void onError(int code, String msg);

    /**
     * 获得实体类的class
     * @return class
     */
    Class<E> getEClass();

    /**
     * 请求成功返回的实体
     */
    void onSuccess(List<E> list);

}
