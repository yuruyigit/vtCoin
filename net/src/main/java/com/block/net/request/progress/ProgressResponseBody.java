package com.block.net.request.progress;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @Description 对下载请求返回的正文进行特殊处理，添加进度指示
 * @Author lijie
 * @Date 2017/6/27.
 */
public class ProgressResponseBody extends ResponseBody {
    private ProgressListener listener;//进度监听
    private ResponseBody body;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody body, ProgressListener listener)
    {
        this.body = body;
        this.listener = listener;
    }

    @Override
    public MediaType contentType()
    {
        return body.contentType();
    }

    @Override
    public long contentLength()
    {
        return body.contentLength();
    }

    @Override
    public BufferedSource source()
    {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(body.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException
            {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (listener != null)
                    listener.progress(totalBytesRead, body.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}
