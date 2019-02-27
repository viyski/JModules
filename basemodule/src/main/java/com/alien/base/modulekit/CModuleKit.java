package com.alien.base.modulekit;

import com.alien.base.di.AppComponent;

class CModuleKit {

    private AppComponent component;
    private static CModuleKit instance;

    public static CModuleKit getInstance(){
        if (instance == null){
            synchronized(CModuleKit.class){
                if (instance == null){
                    instance = new CModuleKit();
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