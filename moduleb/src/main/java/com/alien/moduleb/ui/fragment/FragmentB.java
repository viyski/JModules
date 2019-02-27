package com.alien.moduleb.ui.fragment;

import com.alien.base.ui.BaseFragment;
import com.alien.moduleb.R;

public class FragmentB extends BaseFragment {
    @Override
    protected int getLayoutRes() {
        return R.layout.moduleb_fragment_b;
    }

    @Override
    protected void setUp() {

    }

    public static FragmentB newInstance() {
        return new FragmentB();
    }
}
