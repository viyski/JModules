package com.alien.moduled.ui.fragment;

import com.alien.base.ui.BaseFragment;
import com.alien.moduled.R;

public class FragmentD extends BaseFragment {
    @Override
    protected int getLayoutRes() {
        return R.layout.moduled_fragment_d;
    }

    @Override
    protected void setUp() {

    }

    public static FragmentD newInstance(){
        return new FragmentD();
    }
}
