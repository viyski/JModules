package com.alien.base.ui.activity;

import android.app.Activity;
import android.os.Bundle;

public interface IActivityLife {

    void onCreate(Activity activity, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(Bundle outState);

    void onDestroy();

}
