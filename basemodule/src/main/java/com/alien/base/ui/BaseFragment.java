package com.alien.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alien.base.Constants;
import com.alien.base.data.pref.UserSp;
import com.alien.base.di.BaseViewComponent;
import com.alien.base.di.BaseViewModule;
import com.alien.base.di.DaggerBaseViewComponent;
import com.alien.base.http.exception.ApiError;
import com.alien.base.mvp.MVPView;
import com.alien.base.ui.fragment.IBaseFragment;
import com.android.componentlib.router.Jumper;

public abstract class BaseFragment extends Fragment implements IBaseFragment, MVPView {


    protected int page = Constants.START_PAGE;
    protected View mRooView;
    protected Context mContext;
    protected BaseViewComponent mBaseViewComponent;

    protected BaseViewComponent component() {
        if (mBaseViewComponent == null) {
            mBaseViewComponent = DaggerBaseViewComponent.builder()
                    .baseViewModule(new BaseViewModule(getActivity()))
                    .build();
        }
        return mBaseViewComponent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRooView = inflater.inflate(getLayoutRes(), container, false);
        return mRooView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mContext == null) {
            mContext = getContext();
        }
        setUp();
    }

    protected BaseActivity getBaseActivity() {
        return getActivity() != null && getActivity() instanceof BaseActivity  ? (BaseActivity) getActivity() : null;
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext() == null ? mContext : super.getContext();
    }

    protected abstract int getLayoutRes();

    protected abstract void setUp();

    @Override
    public void onError(ApiError error) {
        if(error.code == 1){
            UserSp.ins().clear();
            Jumper.login(getContext());
        }
        showToast(error.msg);
        page--;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveState(Bundle state) {

    }

    @Override
    public void onRestoreState(Bundle savedState) {

    }
}