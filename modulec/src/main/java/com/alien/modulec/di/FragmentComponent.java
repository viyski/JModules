package com.alien.modulec.di;

import com.alien.base.di.BaseViewModule;
import com.alien.base.di.scope.PerView;
import com.alien.modulec.ui.fragment.FragmentC;

import dagger.Component;

@PerView
@Component(dependencies = {ModuleCAppComponent.class}, modules = {BaseViewModule.class})
public interface FragmentComponent {
    
    void inject(FragmentC fragment);
}