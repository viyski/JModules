package com.alien.base;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alien.base.di.BaseAppComponent;
import com.alien.base.modulekit.BaseModuleKit;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.HashMap;

public abstract class BaseApplication extends MultiDexApplication {

    private BaseAppComponent baseAppComponent;

    public BaseAppComponent baseAppComponent() {
        if (baseAppComponent == null)
            baseAppComponent = BaseModuleKit.getInstance().getComponent();

        return baseAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                .tag("LOG_TAG")
                .build()));
        initComponentDi();
        registerRouter();
        registerActivityLifecycleCallbacks(baseAppComponent().activityLifeCallback());

        // 配置httpHeaders
        baseAppComponent().httpConfig().setHttpHeaders(new HashMap<String, String>());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static BaseApplication instance;

    public static BaseApplication getInstance(){
        return instance;
    }

    public abstract void initComponentDi();

    public abstract void registerRouter();
}