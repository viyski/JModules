package com.alien.moduleb;

import com.alien.base.di.AppComponent;
import com.alien.base.modulekit.AppModuleComponentDelegate;
import com.alien.base.modulekit.BModuleKit;
import com.alien.base.modulekit.BaseModuleKit;
import com.alien.moduleb.di.DaggerModuleBAppComponent;
import com.alien.moduleb.serviceimpl.BusinessBServiceImpl;
import com.android.componentlib.applicationlike.IApplicationLike;
import com.android.componentlib.router.Router;
import com.android.componentservice.moduleb.BusinessBService;

public class BAppLike implements IApplicationLike {
    @Override
    public void onCreate() {
        Router.getInstance().addService(BusinessBService.class, new BusinessBServiceImpl());
        BModuleKit.getInstance().init(new AppModuleComponentDelegate() {

            @Override
            public AppComponent initAppComponent() {
                return DaggerModuleBAppComponent.builder()
                        .baseAppComponent(BaseModuleKit.getInstance().getComponent())
                        .build();
            }
        });
    }

    @Override
    public void onStop() {

    }
}
