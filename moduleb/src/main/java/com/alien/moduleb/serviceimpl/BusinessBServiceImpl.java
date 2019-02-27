package com.alien.moduleb.serviceimpl;


import android.support.v4.app.Fragment;

import com.alien.moduleb.ui.fragment.FragmentB;
import com.android.componentservice.moduleb.BusinessBService;

public class BusinessBServiceImpl implements BusinessBService {

    @Override
    public String getBusinessFlag() {
        return "com.alien.moduleb.service.b";
    }

    @Override
    public Fragment getFragment() {
        return FragmentB.newInstance();
    }
}