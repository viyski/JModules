package com.alien.base.event;

public class BaseEvent {

    public static final int LOGIN_SUCCESS = 888;

    public int type;
    public Object object;

    public BaseEvent(int type, Object object){
        this.type = type;
        this.object = object;
    }
}
