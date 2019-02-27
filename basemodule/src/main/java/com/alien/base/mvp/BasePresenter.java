package com.alien.base.mvp;

public interface BasePresenter<T extends MVPView> {

    void onAttach(T view);

    void onDetach();
}
