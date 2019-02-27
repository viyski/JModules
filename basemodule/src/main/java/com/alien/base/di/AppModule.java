package com.alien.base.di;

import android.app.Application;
import android.content.Context;

import com.alien.base.tools.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SingletonModule.class})
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}