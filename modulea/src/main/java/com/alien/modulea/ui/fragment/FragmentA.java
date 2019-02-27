package com.alien.modulea.ui.fragment;


import com.alien.base.ui.BaseFragment;
import com.alien.modulea.R;

public class FragmentA extends BaseFragment {

    @Override
    public int getLayoutRes() {
     return  R.layout.modulea_fragment_a;
    }

    @Override
    protected void setUp() {

    }

    public static FragmentA newInstance(){
        return new FragmentA();
    }

}