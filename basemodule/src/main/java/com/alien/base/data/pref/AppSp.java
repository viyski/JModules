package com.alien.base.data.pref;

import android.content.Context;

import com.alien.base.modulekit.BaseModuleKit;

public class AppSp extends BasePref {

    private static final String UUID = "app_uuid";
    private static AppSp ins;

    private AppSp(Context context){
        init(context, BasePref.PREF_APP);
    }

    public static AppSp ins(){
        if (ins == null)
            ins = new AppSp(BaseModuleKit.getInstance().getApplication());
        return ins;
    }


    public String uuid(){
        return getString(UUID);
    }

    public void putUuid(String uuid){
        putString(UUID, uuid);
    }

}
