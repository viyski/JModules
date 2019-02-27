package com.alien.modulec.di;

import com.alien.base.di.AppComponent;
import com.alien.base.di.BaseAppComponent;
import com.alien.base.di.scope.AppScope;
import com.alien.base.event.RxBus;

import dagger.Component;


@AppScope
@Component(dependencies = {BaseAppComponent.class}, modules = {ModuleCAppModule.class})
public interface ModuleCAppComponent extends AppComponent {

    RxBus rxBus();

}