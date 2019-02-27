package com.alien.base.tools;

import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtils {

    public static String getToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(new Date());
    }
}