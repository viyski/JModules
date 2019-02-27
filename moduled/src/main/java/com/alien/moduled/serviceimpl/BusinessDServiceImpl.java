package com.alien.moduled.serviceimpl;

import android.support.v4.app.Fragment;

import com.alien.moduled.ui.fragment.FragmentD;
import com.android.componentservice.moduled.BusinessDService;

public class BusinessDServiceImpl implements BusinessDService {
    @Override
    public String getBusinessFlag() {
        return "com.alien.moduled.service.d";
    }

    @Override
    public Fragment getFragment() {
        return FragmentD.newInstance();
    }
}
