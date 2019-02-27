package com.alien.base.modulekit;

import com.alien.base.di.AppComponent;

class DModuleKit {

    private AppComponent component;
    private static DModuleKit instance;

    public static DModuleKit getInstance(){
        if (instance == null){
            synchronized(DModuleKit.class){
                if (instance == null){
                    instance = new DModuleKit();
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