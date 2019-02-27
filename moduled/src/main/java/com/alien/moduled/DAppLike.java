package com.alien.moduled;

import com.alien.base.di.AppComponent;
import com.alien.base.modulekit.AppModuleComponentDelegate;
import com.alien.base.modulekit.BModuleKit;
import com.alien.base.modulekit.BaseModuleKit;
import com.alien.moduled.di.DaggerModuleDAppComponent;
import com.alien.moduled.serviceimpl.BusinessDServiceImpl;
import com.android.componentlib.applicationlike.IApplicationLike;
import com.android.componentlib.router.Router;
import com.android.componentservice.moduled.BusinessDService;

public class DAppLike implements IApplicationLike {

    @Override
    public void onCreate() {
        Router.getInstance().addService(BusinessDService.class, new BusinessDServiceImpl());
        BModuleKit.getInstance().init(new AppModuleComponentDelegate() {
            @Override
            public AppComponent initAppComponent() {
                return DaggerModuleDAppComponent.builder()
                        .baseAppComponent(BaseModuleKit.getInstance().getComponent())
                        .build();
            }
        });
    }

    @Override
    public void onStop() {

    }
}
