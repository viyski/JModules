package com.alien.base.di;

import android.support.v4.util.SimpleArrayMap;

import com.alien.base.http.HttpConfig;
import com.alien.base.tools.permission.PermissionManager;
import com.alien.base.ui.ActivityListManager;
import com.alien.base.ui.activity.ActivityLife;
import com.alien.base.ui.activity.IActivityLife;
import com.alien.base.ui.fragment.FragmentLife;
import com.alien.base.ui.fragment.IFragmentLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OtherModule {

    @Provides
    public HttpConfig httpConfig() {
        return new HttpConfig();
    }

    @Singleton
    @Provides
    public PermissionManager permissionManager()  {
        return new PermissionManager();
    }

    @Singleton
    @Provides
    public ActivityListManager activityListManager() {
        return new ActivityListManager();
    }

    @Singleton
    @Provides
    public SimpleArrayMap<String, IActivityLife> iActivityLifes() {
        return new SimpleArrayMap();
    }

    @Provides
    public IActivityLife iActivityLife() {
        return new ActivityLife();
    }

    @Singleton
    @Provides
    public SimpleArrayMap<String, IFragmentLife> iFragmentLifes() {
        return new SimpleArrayMap();
    }

    @Provides
    public IFragmentLife iFragmentLife() {
        return new FragmentLife();
    }

}