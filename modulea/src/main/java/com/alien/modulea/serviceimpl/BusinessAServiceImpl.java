package com.alien.modulea.serviceimpl;


import android.support.v4.app.Fragment;

import com.alien.modulea.ui.fragment.FragmentA;
import com.alien.modulea.ui.fragment.FragmentE;
import com.android.componentservice.modulea.BusinessAService;

public class BusinessAServiceImpl implements BusinessAService {

    @Override
    public Fragment getFragmentE() {
        return FragmentE.newInstance();
    }

    @Override
    public String getBusinessFlag() {
        return "com.alien.modulea.service.a";
    }

    @Override
    public Fragment getFragment() {
        return FragmentA.newInstance();
    }
}