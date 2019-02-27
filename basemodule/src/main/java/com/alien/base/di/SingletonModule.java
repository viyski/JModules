package com.alien.base.di;

import com.alien.base.Constants;
import com.alien.base.event.RxBus;
import com.alien.base.http.HttpApiService;
import com.alien.base.http.RestApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SingletonModule {

    @Provides
    @Singleton
    public RxBus rxBus(){
        return RxBus.getInstance();
    }

    @Provides
    @Singleton
    public HttpApiService provideHttpApiService(RestApi restApi)  {
        return restApi.retrofitNet(Constants.BASE_URL).create(HttpApiService.class);
    }
}