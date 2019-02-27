package com.alien.base.http.response;


import com.alien.base.http.exception.ApiError;
import com.alien.base.http.exception.HttpThrowable;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class HttpResponseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if(t instanceof HttpResult){
            HttpResult result = (HttpResult) t;
            if(result.state){
                if (result.data != null)
                    onResult(t);
                else{
                    HttpThrowable httpThrowable = new HttpThrowable(result.result, result.msg);
                    onError(new ApiError(httpThrowable));
                }
            }else{
                HttpThrowable httpThrowable = new HttpThrowable(result.result, result.msg);
                onError(new ApiError(httpThrowable));
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