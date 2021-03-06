package cent.news.com.baseframe.modules.download;

import android.text.format.DateUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;

/**
 * Created by bym on 2018/6/20.
 */

public class BaseDownloadRequestQueue {

    /** 调度的线程数量 */
    private static final int						DEFAULT_DOWNLOAD_THREAD_POOL_SIZE	= 1;

    /**
     * 请求集合
     */
    private Set<BaseRequest> mCurrentRequests					= new HashSet<>();

    /** 阻塞队列. */
    private PriorityBlockingQueue<BaseRequest>	mDownloadQueue						= new PriorityBlockingQueue<>();

    /** 调度线程 */
    private SKYDownloadDispatcher[]					mDownloadDispatchers;

    /**
     * 用于生成单调增加的序列号
     */
    private AtomicInteger mSequenceGenerator					= new AtomicInteger();

    /** 超时时间 */
    private static final int						DEFAULT_TIMEOUT						= (int) (20 * DateUtils.SECOND_IN_MILLIS);

    /**
     * 底层网络协议
     */
    private OkHttpClient okHttpClient;

    /**
     * 创建工作线程 DEFAULT_DOWNLOAD_THREAD_POOL_SIZE
     */
    public BaseDownloadRequestQueue() {
        okHttpClient = getOkHttpClient();
        mDownloadDispatchers = new SKYDownloadDispatcher[DEFAULT_DOWNLOAD_THREAD_POOL_SIZE];
    }

    /**
     * 创建工作线程
     *
     * @param threadPoolSize
     *            工作线程数量
     */
    public BaseDownloadRequestQueue(int threadPoolSize) {
        okHttpClient = getOkHttpClient();
        if (threadPoolSize > 0 && threadPoolSize <= 4) {
            mDownloadDispatchers = new SKYDownloadDispatcher[threadPoolSize];
        } else {
            mDownloadDispatchers = new SKYDownloadDispatcher[DEFAULT_DOWNLOAD_THREAD_POOL_SIZE];
        }
    }

    /**
     * 启动工作线程
     */
    public void start() {
        stop();// 停止
        for (int i = 0; i < mDownloadDispatchers.length; i++) {
            SKYDownloadDispatcher downloadDispatcher = new SKYDownloadDispatcher(mDownloadQueue, okHttpClient);
            mDownloadDispatchers[i] = downloadDispatcher;
            downloadDispatcher.start();
        }
    }

    /**
     * 添加请求
     *
     * @param request
     *            请求
     * @return 请求ID
     */
    int add(BaseRequest request) {
        int downloadId = getDownloadId();
        request.setDownloadRequestQueue(this);
        synchronized (mCurrentRequests) {
            mCurrentRequests.add(request);
        }
        request.setRequestId(downloadId);
        request.setRequestTag(String.valueOf(downloadId));
        mDownloadQueue.add(request);

        return downloadId;
    }

    /**
     * 获取下载状态
     */
    int query(int downloadId) {
        synchronized (mCurrentRequests) {
            for (BaseRequest request : mCurrentRequests) {
                if (request.getRequestId() == downloadId) {
                    return request.getDownloadState();
                }
            }
        }
        return BaseDownloadManager.STATUS_NOT_FOUND;
    }

    /**
     * 取消所有请求
     */
    void cancelAll() {
        synchronized (mCurrentRequests) {
            for (BaseRequest request : mCurrentRequests) {
                request.cancel();
            }
            mCurrentRequests.clear();
        }
    }

    /**
     * 取消请求
     */
    int cancel(int downloadId) {
        synchronized (mCurrentRequests) {
            for (BaseRequest request : mCurrentRequests) {
                if (request.getRequestId() == downloadId) {
                    request.cancel();
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 从队列中删除
     *
     * @param request
     *            请求
     */
    void finish(BaseRequest request) {
        if (mCurrentRequests != null) {
            synchronized (mCurrentRequests) {
                mCurrentRequests.remove(request);
            }
        }
    }

    /**
     * 取消所有待决运行的请求，并释放所有的工作线程
     */
    void release() {
        if (mCurrentRequests != null) {
            synchronized (mCurrentRequests) {
                mCurrentRequests.clear();
                mCurrentRequests = null;
            }
        }

        if (mDownloadQueue != null) {
            mDownloadQueue = null;
        }

        if (mDownloadDispatchers != null) {
            stop();

            for (int i = 0; i < mDownloadDispatchers.length; i++) {
                mDownloadDispatchers[i] = null;
            }
            mDownloadDispatchers = null;
        }

    }

    /**
     * 停止所有工作线程
     */
    private void stop() {
        for (int i = 0; i < mDownloadDispatchers.length; i++) {
            if (mDownloadDispatchers[i] != null) {
                mDownloadDispatchers[i].quit();
            }
        }
    }

    /**
     * 获取下载ID
     */
    private int getDownloadId() {
        return mSequenceGenerator.incrementAndGet();
    }

    /**
     * 获取底层网络协议
     *
     * @return 网络协议
     */
    public final OkHttpClient getOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)// 连接超时
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)// 读取超时
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)// 写入超时
                .build();
        return okHttpClient;
    }


}
