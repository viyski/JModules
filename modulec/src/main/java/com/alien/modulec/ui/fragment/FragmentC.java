package com.alien.modulec.ui.fragment;

import com.alien.base.ui.BaseFragment;
import com.alien.modulec.R;

public class FragmentC extends BaseFragment {
    @Override
    protected int getLayoutRes() {
        return R.layout.modulec_fragment_c;
    }

    @Override
    protected void setUp() {

    }

    public static FragmentC newInstance(){
        return new FragmentC();
    }
}
