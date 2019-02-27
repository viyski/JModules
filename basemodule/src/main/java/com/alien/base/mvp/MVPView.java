package com.alien.base.mvp;

import android.content.Context;

import com.alien.base.http.exception.ApiError;

public interface MVPView {

    Context context();

    void onError(ApiError error);

    void showToast(String text);
}
