package com.alien.base.ui.fragment;

import android.os.Bundle;

public interface IBaseFragment {

    void onSaveState(Bundle state);

    void onRestoreState(Bundle savedState);
}
