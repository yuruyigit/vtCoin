package com.block.net.mvp.view;

import java.util.Map;

/**
 * @Description view 层，传一个实体类的对象进来
 */
public interface ICommonView<E> extends ILoadingView {
    /**
     * 请求的参数
     * @return map
     */
    Map<String,Object> getParameters();


    /**
     * 请求成功返回的实体
     * @param entity 实体
     */
    void onCompleted(E entity);

    /**
     * 请求错误
     * @param code 错误码
     * @param msg 错误信息
     */
    void onError(int code, String msg);
    /**
     * 获取action路径
     * @return String
     */
    String getUrlAction();

    /**
     * 获得实体类的class
     * @return class
     */
    Class<E> getEClass();
}
