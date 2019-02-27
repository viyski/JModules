package com.alien.jmodules;

import com.alien.base.BaseApplication;
import com.android.componentlib.router.Jumper;
import com.android.componentlib.router.Router;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Router.registerComponent("com.alien.modulea.AAppLike");
        Router.registerComponent("com.alien.moduleb.BAppLike");
        Router.registerComponent("com.alien.modulec.CAppLike");
        Router.registerComponent("com.alien.moduled.DAppLike");
    }

    @Override
    public void initComponentDi() {

    }

    @Override
    public void registerRouter() {
        Jumper.init(BuildConfig.DEBUG, this);
    }
}