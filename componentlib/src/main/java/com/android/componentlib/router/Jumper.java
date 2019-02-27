package com.android.componentlib.router;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

public class Jumper {

    public static void init(boolean debug, Application application){
        if (debug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(application);
    }

    public static boolean check(Context context, long uid){
        if (uid == 0L){
            login(context);
            return false;
        }
        return true;
    }

    public static void login(Context context){
        ARouter.getInstance().build(RouterPath.PATH_LOGIN).navigation(context);
    }

    public static void main(){
        ARouter.getInstance().build(RouterPath.MAIN_PATH).navigation();
    }
}
