package com.alien.modulea;


import com.alien.base.di.AppComponent;
import com.alien.base.modulekit.AModuleKit;
import com.alien.base.modulekit.AppModuleComponentDelegate;
import com.alien.base.modulekit.BaseModuleKit;
import com.alien.modulea.di.DaggerModuleAAppComponent;
import com.alien.modulea.serviceimpl.BusinessAServiceImpl;
import com.android.componentlib.applicationlike.IApplicationLike;
import com.android.componentlib.router.Router;
import com.android.componentservice.modulea.BusinessAService;

public class AAppLike implements IApplicationLike {

    @Override
    public void onCreate() {
        Router.getInstance().addService(BusinessAService.class, new BusinessAServiceImpl());
        AModuleKit.getInstance().init(new AppModuleComponentDelegate() {

            @Override
            public AppComponent initAppComponent() {
                return DaggerModuleAAppComponent.builder()
                        .baseAppComponent(BaseModuleKit.getInstance().getComponent())
                        .build();
            }
        });
    }

    @Override
    public void onStop() {

    }
}