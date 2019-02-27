package com.alien.baselib.support;

import android.support.annotation.IntDef;
import android.support.design.widget.AppBarLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public static final int EXPANDED = 0;
    public static final int COLLAPSED = 1;
    public static final int IDLE = 2;
    private @State int state = IDLE;

    @IntDef({EXPANDED, COLLAPSED, IDLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State{

    }

    public abstract void onStateChanged(AppBarLayout appBar, @State int state, int offset);


    @Override
    public void onOffsetChanged(AppBarLayout appBar, int offset) {
        if (offset == 0){
            if (state != EXPANDED){
                onStateChanged(appBar, EXPANDED, offset);
            }
            state = EXPANDED;
        }else if (Math.abs(offset) >= appBar.getTotalScrollRange()){
            if (state != COLLAPSED){
                onStateChanged(appBar, COLLAPSED, offset);
            }
            state = COLLAPSED;
        }else{
            onStateChanged(appBar, IDLE, offset);
            state = IDLE;
        }
    }
}
