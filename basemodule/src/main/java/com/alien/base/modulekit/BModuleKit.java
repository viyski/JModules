package com.alien.base.modulekit;

import com.alien.base.di.AppComponent;

public class BModuleKit {

    private AppComponent component;
    private static BModuleKit instance;

    public static BModuleKit getInstance(){
        if (instance == null){
            synchronized(BModuleKit.class){
                if (instance == null){
                    instance = new BModuleKit();
                }
            }
        }
        return instance;
    }

    public void init(AppModuleComponentDelegate delegate){
        component = delegate.initAppComponent();
    }

    public AppComponent getComponent() {
        return component;
    }

}