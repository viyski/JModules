package com.alien.base.modulekit;

import com.alien.base.di.AppComponent;

public class AModuleKit {

    private AppComponent component;
    private static AModuleKit instance;

    public static AModuleKit getInstance(){
        if (instance == null){
            synchronized(AModuleKit.class){
                if (instance == null){
                    instance = new AModuleKit();
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