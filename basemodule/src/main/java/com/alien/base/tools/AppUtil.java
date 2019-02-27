package com.alien.base.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.alien.base.Constants;

import java.util.List;

public class AppUtil {

    @SuppressLint("MissingPermission")
    public static String getImei(Context context){
        String imei = "";
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                imei = manager.getImei();
            }else{
                imei = manager.getDeviceId();
            }
            if (TextUtils.isEmpty(imei)){
                imei = getAndroidId(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    public static String getAndroidId(Context context) {
        String aId = "";
        try {
            aId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
        }

        return aId;
    }

    public static String getChannel(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return applicationInfo.metaData.getString("umeng_channel", "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean hasMore(List list) {
        return list != null && list.size() >= Constants.PAGE_MAX_SIZE;
    }

    public static int statusBarHeight() {
        return Resources.getSystem()
                .getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }
}