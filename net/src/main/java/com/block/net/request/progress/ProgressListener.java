package com.block.net.request.progress;

/**
 * @Description
 * @Author lijie
 * @Date 2017/3/14.
 */
public interface ProgressListener {
    /**
     * 下载请求进度回调接口
     * @param progress 进度
     * @param total   总大小
     * @param done   是否完成
     */
    void progress(long progress, long total, boolean done);
}
