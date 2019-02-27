package com.alien.base.tools.permission;

public interface PermissionListener {

    void onGranted(String permissionName);

    void onDenied(String permissionName);

    void onDeniedWithNeverAsk(String permissionName);
}