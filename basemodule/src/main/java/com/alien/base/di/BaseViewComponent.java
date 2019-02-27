package com.alien.base.di;

import android.app.Activity;
import com.alien.base.di.scope.PerView;
import com.alien.base.ui.AbstractActivity;
import com.alien.base.ui.BaseFragment;
import dagger.Component;

@PerView
@Component(modules = BaseViewModule.class)
public interface BaseViewComponent {

    Activity activity();

    void inject(AbstractActivity activity);

    void inject(BaseFragment fragment);

}