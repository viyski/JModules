package com.alien.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alien.base.Constants;
import com.alien.base.data.pref.UserSp;
import com.alien.base.http.exception.ApiError;
import com.android.componentlib.router.Jumper;

public abstract class BaseActivity extends AbstractActivity {

    public static final int RESULT_DELETE = 2;
    public static final int RESULT_QR_SUCCESS = 3;
    public static final int RESULT_EDIT = 5;

    protected int page = Constants.START_PAGE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResLayout() != 0) {
            setContentView(getResLayout());
        }
        onCreateInit(savedInstanceState);
    }

    protected abstract void onCreateInit(Bundle savedInstanceState);

    protected abstract int getResLayout();

    @Override
    public boolean isUseFragment() {
        return false;
    }

    @Override
    public void onError(ApiError error) {
        if(error.code == 1){
            UserSp.ins().clear();
            Jumper.login( this);
        }
        showToast(error.msg);
        page--;
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}