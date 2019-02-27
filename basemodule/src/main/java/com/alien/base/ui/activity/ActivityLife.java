package com.alien.base.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.alien.base.modulekit.BaseModuleKit;
import com.alien.base.ui.ActivityListManager;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.subjects.PublishSubject;

public class ActivityLife implements IActivityLife {

    private Activity mActivity;
    private final PublishSubject<ActivityEvent> mLifecycleSubject = PublishSubject.create();

    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        mActivity = activity;

        mLifecycleSubject.onNext(ActivityEvent.CREATE);

        boolean isNotAdd = false;
        if (activity.getIntent() != null) isNotAdd = activity.getIntent().getBooleanExtra(ActivityListManager.IS_NOT_ADD_ACTIVITY_LIST, false);

        if (!isNotAdd) BaseModuleKit.getInstance().activityListManager().addActivity(activity);
    }

    @Override
    public void onStart() {
        mLifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    public void onResume() {
        BaseModuleKit.getInstance().activityListManager().setCurrentActivity(mActivity);
        mLifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    public void onPause() {
        mLifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    public void onStop() {
        if (BaseModuleKit.getInstance().activityListManager().getCurrentActivity() == mActivity) {
            BaseModuleKit.getInstance().activityListManager().setCurrentActivity(null);
        }
        mLifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onDestroy() {
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);

        BaseModuleKit.getInstance().activityListManager().removeActivity(mActivity);
        mActivity = null;
    }

    public PublishSubject<ActivityEvent> getLifecycleSubject() {
        return mLifecycleSubject;
    }
}
