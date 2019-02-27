package com.alien.base.http;

import android.support.annotation.NonNull;

import com.alien.base.Platform;
import java.util.Map;

public class HttpConfig {

    public int platform = Platform.PF_A;

    public Map<String, String> headerMap;

    public void setHttpHeaders(@NonNull Map<String, String> map){
        headerMap = map;
    }
}