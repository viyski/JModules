package com.alien.base.modulekit;

import com.alien.base.di.AppComponent;

class EModuleKit {

    private AppComponent component;
    private static EModuleKit instance;

    public static EModuleKit getInstance(){
        if (instance == null){
            synchronized(EModuleKit.class){
                if (instance == null){
                    instance = new EModuleKit();
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