package com.alien.base.http;


import android.annotation.SuppressLint;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alien.base.BuildConfig;
import com.alien.base.http.interceptor.HttpCacheInterceptor;
import com.alien.base.http.interceptor.HttpHeaderInterceptor;
import com.alien.base.http.response.JsonResponseObserver;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestApi {

    public static final long CONNECT_TIME_OUT = 30L;
    public static final long READ_TIME_OUT = 30L;
    public static final long WRITE_TIME_OUT = 30L;
    public static final long KEEP_ALIVE_TIME = 60L;
    public static final int MAX_POOL_SIZE = 10;
    public static final int INITIAL_CORE_POOL_SIZE = 1;

    private final Context context;
    private final HttpConfig httpConfig;

    private boolean DEBUG = BuildConfig.DEBUG;
    private int CORE_POOL_SIZE = Math.min(MAX_POOL_SIZE, Runtime.getRuntime().availableProcessors() + 1);

    private ThreadPoolExecutor HTTP_EXECUTOR = new ThreadPoolExecutor(INITIAL_CORE_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private OkHttpClient mOkHttpClient;


    @Inject
    public RestApi(Context context, HttpConfig httpConfig){
        this.context = context;
        this.httpConfig = httpConfig;
    }


    public Retrofit retrofitNet(String baseUrl) {
        return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .client(setupOkHttpClient())
            .build();
    }

    public OkHttpClient setupOkHttpClient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = getHttpLoggingInterceptor();
            HttpCacheInterceptor cacheInterceptor = new HttpCacheInterceptor(context, 0, 0);

            mOkHttpClient = new OkHttpClient.Builder()
                .dispatcher(new Dispatcher(HTTP_EXECUTOR))
                .cache(getHttpCache())
                .addInterceptor(new HttpHeaderInterceptor(httpConfig.headerMap))
                .addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
        }

        return mOkHttpClient;
    }

    private Cache getHttpCache() {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        return new Cache(httpCacheDirectory, 10 * 1024 * 1024);
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level logLevel = DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC;
        loggingInterceptor.setLevel(logLevel);
        return loggingInterceptor;
    }

    @SuppressLint("CheckResult")
    public <T> void okhttpCall(String url, Object requestBody, JsonResponseObserver<T> observer){
        final OkHttpClient okHttpClient = setupOkHttpClient();
        final Request request = new Request.Builder()
            .url(url)
            .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(requestBody)))
            .build();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> it) throws Exception {
                okHttpClient.newCall(request).enqueue(new Callback(){
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()) {
                            it.onNext(response.body().string());
                        }else{
                            it.onError(new RuntimeException("http exception"));
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        it.onError(e);
                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(observer);
    }

}