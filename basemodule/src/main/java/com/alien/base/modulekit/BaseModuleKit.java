package com.alien.base.modulekit;

import android.app.Application;

import com.alien.base.BaseApplication;
import com.alien.base.di.AppModule;
import com.alien.base.di.BaseAppComponent;
import com.alien.base.di.DaggerBaseAppComponent;
import com.alien.base.ui.ActivityListManager;

public class BaseModuleKit {

    private BaseAppComponent component;
    private Application application;
    private static BaseModuleKit instance;


    public static BaseModuleKit getInstance(){
        if (instance == null){
            synchronized(BaseModuleKit.class){
                if (instance == null){
                    instance = new BaseModuleKit();
                    instance.application = BaseApplication.getInstance();
                    instance.component = DaggerBaseAppComponent.builder()
                            .appModule(new AppModule(instance.application))
                                .build();
                }
            }
        }

        return instance;
    }

    public BaseAppComponent  getComponent() {
        return component;
    }

    public Application getApplication() {
      return application;
    }

    public ActivityListManager activityListManager() {
        return component.activityListManager();
    }
}