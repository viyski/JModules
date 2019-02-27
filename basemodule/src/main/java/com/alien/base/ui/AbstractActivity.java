package com.alien.base.ui;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alien.base.di.BaseViewComponent;
import com.alien.base.di.BaseViewModule;
import com.alien.base.di.DaggerBaseViewComponent;
import com.alien.base.mvp.MVPView;
import com.alien.base.ui.activity.IBaseActivity;
import com.github.zackratos.ultimatebar.UltimateBar;

public abstract class AbstractActivity extends AppCompatActivity implements IBaseActivity, MVPView {
    private BaseViewComponent mBaseViewComponent;

    public BaseViewComponent component() {
        if (mBaseViewComponent == null) {
            mBaseViewComponent = DaggerBaseViewComponent.builder()
                    .baseViewModule(new BaseViewModule(this))
                    .build();
        }
        return mBaseViewComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFontScale();
        component().inject(this);
        new UltimateBar.Builder(this).statusDark(true)
                .statusDrawable(new ColorDrawable(statusColor()))
                .create()
                .drawableBar();

        if (isFullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = 1f;
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getResources().updateConfiguration(configuration, metrics);
    }

    @Override
    public int statusColor() {
        return Color.WHITE;
    }

    @Override
    public boolean isFullScreen() {
        return false;
    }

    @Override
    public boolean isImmersionBar() {
        return false;
    }
}