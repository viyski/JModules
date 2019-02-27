package com.alien.base.http.interceptor;

import android.content.Context;

import com.alien.base.tools.NetWorkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ReeseLuo on 2019/1/6.
 */
public class HttpCacheInterceptor implements Interceptor {

    private final Context context;
    private int mCacheTimeWithNet;
    private int mCacheTimeWithoutNet;

    public HttpCacheInterceptor(Context context, int cacheTimeWithNet, int cacheTimeWithoutNet){
        this.context = context;
        mCacheTimeWithNet = cacheTimeWithNet;
        mCacheTimeWithoutNet = cacheTimeWithoutNet;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkUtils.isNetAvailable(context)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }

        Response response = chain.proceed(request);
        if (NetWorkUtils.isNetAvailable(context)) {
            int maxAge = mCacheTimeWithNet > 0 ? mCacheTimeWithNet : 60;
            String cacheControl = "public,max-age=" + maxAge;
            return response.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
        } else {
            int maxStale = mCacheTimeWithoutNet > 0 ? mCacheTimeWithoutNet : 60 * 60 * 24 * 7 * 1;
            return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale).removeHeader("Pragma").build();
        }
    }

}