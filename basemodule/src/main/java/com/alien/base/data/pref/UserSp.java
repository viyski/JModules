package com.alien.base.data.pref;

import android.content.Context;

import com.alien.base.data.entity.User;
import com.alien.base.modulekit.BaseModuleKit;

public class UserSp extends BasePref {

    private static final String UID = "uid";
    private static final String LOGIN_KEY = "loginKey";
    private static final String TOKEN = "token";
    private static final String USER_NAME = "user_name";
    private static final String USER_HEAD = "user_head";
    private static final String USER_ABOUT = "user_about";
    private static UserSp ins;

    private UserSp(Context context){
        init(context, BasePref.PREF_USER);
    }

    public static UserSp ins(){
        if (ins == null)
            ins = new UserSp(BaseModuleKit.getInstance().getApplication());
        return ins;
    }

    public long uid() {
        return getLong(UID);
    }

    public String loginKey() {
        return getString(LOGIN_KEY);
    }

    public String token() {
        return getString(TOKEN);
    }

    public String name() {
        return getString(USER_NAME);
    }

    public String head() {
        return getString(USER_HEAD);
    }

    public String about() {
        return  getString(USER_ABOUT);
    }

    public void saveUser(User user, String loginKey) {
        putLong(UID, user.uid);
        putString(LOGIN_KEY, loginKey);
    }
}
