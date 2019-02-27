package com.alien.modulea.ui.fragment;

import com.alien.base.modulekit.AModuleKit;
import com.alien.base.ui.BaseFragment;
import com.alien.modulea.di.DaggerFragmentComponent;
import com.alien.modulea.di.FragmentComponent;
import com.alien.modulea.di.ModuleAAppComponent;

public abstract class BaseInjectFragment extends BaseFragment {

    protected FragmentComponent fragmentComponent() {
        return DaggerFragmentComponent.builder()
            .moduleAAppComponent((ModuleAAppComponent) AModuleKit.getInstance().getComponent())
            .build();
    }
}