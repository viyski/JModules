package com.alien.base.tools.permission;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class PermissionManager {

    @SuppressLint("CheckResult")
    public void requestEach(FragmentActivity activity, final PermissionListener listener, String permissions) {
        if (activity != null) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        listener.onGranted(permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        listener.onDenied(permission.name);
                    } else {
                        listener.onDeniedWithNeverAsk(permission.name);
                    }
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    public void requestEachCombined(FragmentActivity activity, final PermissionListener listener, String...permissions) {
        if (activity != null) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        listener.onGranted(permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        listener.onDenied(permission.name);
                    } else {
                        listener.onDeniedWithNeverAsk(permission.name);
                    }
                }
            });
        }
    }

}