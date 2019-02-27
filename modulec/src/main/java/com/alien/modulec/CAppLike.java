package com.alien.modulec;

import com.alien.base.di.AppComponent;
import com.alien.base.modulekit.AppModuleComponentDelegate;
import com.alien.base.modulekit.BModuleKit;
import com.alien.base.modulekit.BaseModuleKit;
import com.alien.modulec.di.DaggerModuleCAppComponent;
import com.alien.modulec.serviceimpl.BusinessCServiceImpl;
import com.android.componentlib.applicationlike.IApplicationLike;
import com.android.componentlib.router.Router;
import com.android.componentservice.modulec.BusinessCService;

public class CAppLike implements IApplicationLike {

    @Override
    public void onCreate() {
        Router.getInstance().addService(BusinessCService.class, new BusinessCServiceImpl());
        BModuleKit.getInstance().init(new AppModuleComponentDelegate() {

            @Override
            public AppComponent initAppComponent() {
                return DaggerModuleCAppComponent.builder()
                        .baseAppComponent(BaseModuleKit.getInstance().getComponent())
                        .build();
            }
        });
    }

    @Override
    public void onStop() {

    }
}
