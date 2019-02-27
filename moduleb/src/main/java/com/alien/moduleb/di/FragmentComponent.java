package com.alien.moduleb.di;


import com.alien.base.di.BaseViewModule;
import com.alien.base.di.scope.PerView;
import com.alien.moduleb.ui.fragment.FragmentB;

import dagger.Component;

@PerView
@Component(dependencies = {ModuleBAppComponent.class}, modules = {BaseViewModule.class})
public interface FragmentComponent {
    
    void inject(FragmentB fragment);
}