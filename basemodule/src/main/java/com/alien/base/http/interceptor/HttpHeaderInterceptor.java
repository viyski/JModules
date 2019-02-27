package com.alien.base.http.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor {

    private final Map<String, String> headers;

    public HttpHeaderInterceptor(Map<String, String> headers){
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder originalBuilder = originalRequest.newBuilder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            originalBuilder.header(entry.getKey(), entry.getValue());
        }

        Request.Builder newBuilder = originalBuilder.method(originalRequest.method(), originalRequest.body());
        return chain.proceed(newBuilder.build());
    }
}