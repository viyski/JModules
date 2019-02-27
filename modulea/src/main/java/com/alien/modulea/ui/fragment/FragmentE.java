package com.alien.modulea.ui.fragment;


import com.alien.base.ui.BaseFragment;
import com.alien.modulea.R;

public class FragmentE extends BaseFragment {

    @Override
    public int getLayoutRes() {
        return  R.layout.modulea_fragment_e;
    }

    @Override
    protected void setUp() {

    }

    public static FragmentE newInstance(){
        return new FragmentE();
    }

}