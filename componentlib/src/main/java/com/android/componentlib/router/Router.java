package com.android.componentlib.router;

import android.text.TextUtils;

import com.android.componentlib.applicationlike.IApplicationLike;

import java.util.HashMap;

public class Router {

    private HashMap<String, Object> services = new HashMap();
    private static Router instance;
    private static HashMap<String, IApplicationLike> components = new HashMap<>();

    private Router() {

    }

    public static Router getInstance() {
        if (instance == null) {
            synchronized (Router.class) {
                if (instance == null)
                    instance = new Router();
            }
        }
        return instance;
    }

    public static void registerComponent(String classname) {
        if (TextUtils.isEmpty(classname)) {
            return;
        }
        if (components.keySet().contains(classname)) {
            return;
        }
        try {
            Class clazz = Class.forName(classname);
            IApplicationLike applicationLike = (IApplicationLike) clazz.newInstance();
            applicationLike.onCreate();
            components.put(classname, applicationLike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterComponent(String classname) {
        if (TextUtils.isEmpty(classname)) {
            return;
        }
        if (components.keySet().contains(classname)) {
            components.get(classname).onStop();
            components.remove(classname);
            return;
        }
        try {
            Class clazz = Class.forName(classname);
            IApplicationLike applicationLike = (IApplicationLike) clazz.newInstance();
            applicationLike.onStop();
            components.remove(classname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized <T> void addService(Class<T> tClass, T t) {
        if (tClass == null || t == null) {
            return;
        }
        services.put(tClass.getSimpleName(), t);
    }

    public synchronized <T> T getService(Class<T> tClass) {
        if (tClass == null || !services.containsKey(tClass.getSimpleName())) {
            return null;
        } else {
            return (T) services.get(tClass.getSimpleName());
        }
    }

    public synchronized <T> void removeService(Class<T> tClass) {
        if (tClass == null || !services.containsKey(tClass.getSimpleName())) {
            return;
        }
        services.remove(tClass.getSimpleName());
    }

}
