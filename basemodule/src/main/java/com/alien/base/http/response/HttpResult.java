package com.alien.base.http.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ReeseLuo on 2019/1/5.
 */
public class HttpResult<T> {

    @Expose
    @SerializedName("result")
    public int result;

    @Expose
    @SerializedName("state")
    public boolean state;

    @Expose
    @SerializedName("msg")
    public String msg;

    @Expose
    @SerializedName("data")
    public T data;

    public HttpResult(int result, boolean state, String msg, T data) {
        this.result = result;
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
}