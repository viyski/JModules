package com.alien.modulec.serviceimpl;

import android.support.v4.app.Fragment;

import com.alien.modulec.ui.fragment.FragmentC;
import com.android.componentservice.modulec.BusinessCService;

public class BusinessCServiceImpl implements BusinessCService {
    @Override
    public String getBusinessFlag() {
        return "com.alien.modulec.service.c";
    }

    @Override
    public Fragment getFragment() {
        return FragmentC.newInstance();
    }
}
