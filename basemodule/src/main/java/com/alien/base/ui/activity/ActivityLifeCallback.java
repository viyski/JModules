package com.alien.base.ui.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.SimpleArrayMap;

import com.alien.base.ui.fragment.FragmentLifeCallback;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Lazy;

@Singleton
public class ActivityLifeCallback implements Application.ActivityLifecycleCallbacks {

    @Inject
    SimpleArrayMap<String, IActivityLife> mMapActivityLife;
    @Inject
    Provider<IActivityLife> mActivityLifeProvider;
    @Inject
    Lazy<FragmentLifeCallback> mFragmentLifeCallbackProvider;

    @Inject
    public ActivityLifeCallback() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity instanceof IBaseActivity) {
            IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
            if (iActivityLife == null) {
                iActivityLife = mActivityLifeProvider.get();
                mMapActivityLife.put(activity.toString(), iActivityLife);
            }
            iActivityLife.onCreate(activity, bundle);
        }

        boolean isUseFragment = activity instanceof IBaseActivity ? ((IBaseActivity) activity).isUseFragment() : true;
        if (activity instanceof FragmentActivity && isUseFragment) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifeCallbackProvider.get(), true);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
        if (iActivityLife != null) {
            iActivityLife.onStart();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
        if (iActivityLife != null) {
            iActivityLife.onResume();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
        if (iActivityLife != null) {
            iActivityLife.onPause();
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
        if (iActivityLife != null) {
            iActivityLife.onStop();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
        if (iActivityLife != null) {
            iActivityLife.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        IActivityLife iActivityLife = mMapActivityLife.get(activity.toString());
        if (iActivityLife != null) {
            iActivityLife.onDestroy();
        }

        mMapActivityLife.remove(activity.toString());
    }

    public ActivityLife getActivityLife(String key) {
        return (ActivityLife) mMapActivityLife.get(key);
    }

}
