package com.alien.base.http.response;


import com.alien.base.http.exception.ApiError;
import com.alien.base.http.exception.HttpThrowable;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class JsonResponseObserver<T> implements Observer<String> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String str) {
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
            T t = new GsonBuilder().create().fromJson(str, clazz);

            if(t instanceof HttpResult){
                HttpResult result = (HttpResult) t;
                if(result.state){
                    if (result.data != null)
                        onResult(t);
                    else{
                        onError(new ApiError(new HttpThrowable(result.result, result.msg)));
                    }
                }else{
                    onError(new ApiError(new HttpThrowable(result.result, result.msg)));
                }
            }else if (t instanceof HttpResponse){
                HttpResponse response = (HttpResponse) t;
                if(response.state){
                    onResult(t);
                }else{
                    onError(new ApiError(new HttpThrowable(response.result, response.msg)));
                }
            }else{
                onResult(t);
            }
        } catch (Exception e) {
            onError(new ApiError(e));
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Logger.t(getClass().getSimpleName()).e(e, "error message : %s", e.getMessage());
        onError(new ApiError(e));
    }

    public abstract void onResult(T result);

    public abstract void onError(ApiError error);
}