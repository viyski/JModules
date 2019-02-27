package com.alien.base.http.exception;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.ConnectException;

import retrofit2.HttpException;

public class ApiError extends Exception {

    public static final String TAG = ApiError.class.getSimpleName();

    public static final int ERROR_TYPE_UNKNOWN = -1;
    public static final int ERROR_TYPE_CONNECT = -2;
    public static final int ERROR_TYPE_HTTP = -3;
    public static final int ERROR_BAD_QEQUEST = 400;
    public static final int ERROR_UNAUTHORIZED = 401;
    public static final int ERROR_FORBIDDEN = 403;

    public Throwable e;
    private Error error;
    public String msg;
    public int code;
    public int errorType;

    public ApiError(Throwable e) {
        this.e = e;
        init();
    }

    public void init() {
        if (e instanceof HttpThrowable) {
            this.errorType = ERROR_TYPE_HTTP;
            this.code = ((HttpThrowable) e).getCode();
            this.msg = e.getMessage();
        } else if (e instanceof HttpException) {
            this.errorType = ERROR_TYPE_HTTP;
            this.code = ((HttpException) e).code();
            this.msg = e.getMessage();
            try {
                String error = ((HttpException) e).response().errorBody().string();
                Logger.e("value %s", error);
                parseErrorBody(error);
            } catch (IOException e1) {
                Logger.t(TAG).e("get message %s", e1);
            } catch (JsonSyntaxException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }

        } else if (e instanceof RuntimeException) {
            this.msg = e.getMessage();
        } else if (e instanceof ConnectException) {
            this.errorType = ERROR_TYPE_CONNECT;
            this.msg = e.getMessage();
        } else {
            this.errorType = ERROR_TYPE_UNKNOWN;
            this.msg = e.getMessage();
            Logger.t(TAG).e("error %s", this);
        }
    }

    private void parseErrorBody(String errorBody) {
        Logger.t(TAG).e("error %s", errorBody);
        if (TextUtils.isEmpty(errorBody)) {
            return;
        }
    }

    class Error {
        public int code;
        public String message;
        public String extra;
    }
}