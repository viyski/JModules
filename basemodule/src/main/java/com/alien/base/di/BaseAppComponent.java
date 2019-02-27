package com.alien.base.di;

import com.alien.base.event.RxBus;
import com.alien.base.http.HttpApiService;
import com.alien.base.http.HttpConfig;
import com.alien.base.http.RestApi;
import com.alien.base.tools.SchedulerProvider;
import com.alien.base.tools.permission.PermissionManager;
import com.alien.base.ui.ActivityListManager;
import com.alien.base.ui.activity.ActivityLifeCallback;
import com.alien.base.ui.fragment.FragmentLifeCallback;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SingletonModule.class, OtherModule.class})
public interface BaseAppComponent {

    RestApi restApi();

    RxBus rxBus();

    HttpApiService apiService();

    PermissionManager permissionManager();

    ActivityListManager activityListManager();

    ActivityLifeCallback activityLifeCallback();

    FragmentLifeCallback fragmentLifeCallback();

    SchedulerProvider schedulerProvider();

    HttpConfig httpConfig(); 

}