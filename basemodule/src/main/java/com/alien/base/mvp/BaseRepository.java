package com.alien.base.mvp;

import com.alien.base.data.pref.AppSp;
import com.alien.base.data.pref.UserSp;

public class BaseRepository implements MVPRepository{

    @Override
    public String uid() {
        return isUserLogin() ? isUid().toString() : isUuid();
    }

    @Override
    public Boolean isUserLogin() {
        return isUid() > 0;
    }

    @Override
    public String isUuid() {
        return AppSp.ins().uuid();
    }

    @Override
    public Long isUid() {
        return UserSp.ins().uid();
    }

    @Override
    public void performUserLogout() {
        UserSp.ins().clear();
    }
}
