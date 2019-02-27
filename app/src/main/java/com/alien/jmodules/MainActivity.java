package com.alien.jmodules;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alien.base.ui.BaseActivity;
import com.alien.baselib.support.ViewPagerAdapter;
import com.alien.baselib.widget.TabRadioLayout;
import com.android.componentlib.router.Router;
import com.android.componentservice.modulea.BusinessAService;
import com.android.componentservice.moduleb.BusinessBService;
import com.android.componentservice.modulec.BusinessCService;
import com.android.componentservice.moduled.BusinessDService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabRadioLayout tabLayout;

    @Override
    protected int getResLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateInit(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(Router.getInstance().getService(BusinessAService.class).getFragment());
        fragments.add(Router.getInstance().getService(BusinessBService.class).getFragment());
        fragments.add(Router.getInstance().getService(BusinessCService.class).getFragment());
        fragments.add(Router.getInstance().getService(BusinessDService.class).getFragment());
        fragments.add(Router.getInstance().getService(BusinessAService.class).getFragmentE());

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, null));
        viewPager.setOffscreenPageLimit(fragments.size());
        tabLayout.addTab(tabLayout.newTab().setTabText(R.string.str_tab_home).setIcon(R.drawable.btn_tab_home));
        tabLayout.addTab(tabLayout.newTab().setTabText(R.string.str_tab_live).setIcon(R.drawable.btn_tab_live));
        tabLayout.addTab(tabLayout.newTab().setTabText(R.string.str_tab_more).setIcon(R.drawable.btn_tab_dynamic));
        tabLayout.addTab(tabLayout.newTab().setTabText(R.string.str_tab_message).setIcon(R.drawable.btn_tab_message));
        tabLayout.addTab(tabLayout.newTab().setTabText(R.string.str_tab_me).setIcon(R.drawable.btn_tab_me));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                tabLayout.onPageSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        tabLayout.addOnTabSelectedListener(new TabRadioLayout.ViewPagerOnTabSelectedListener(viewPager));
    }


}
