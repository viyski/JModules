package com.alien.base.di;

import android.app.Activity;
import com.alien.base.di.scope.PerView;
import dagger.Module;
import dagger.Provides;

@Module
public class BaseViewModule {

    private Activity activity;

    public BaseViewModule(Activity activity){
        this.activity = activity;
    }


    @Provides
    @PerView
    public Activity provideActivity(){
        return activity;
    }
}