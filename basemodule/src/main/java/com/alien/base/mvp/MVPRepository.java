package com.alien.base.mvp;

public interface MVPRepository {

    String uid();

    Boolean isUserLogin();

    String isUuid();

    Long isUid();

    void performUserLogout();
}
