package com.alien.base.tools;

import android.text.TextUtils;
import com.google.common.base.Objects;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isMobile(String mobiles) {
        String telRegex = "\\d{11}";
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    public static String mobileEncr(String mobiles) {
        if (isMobile(mobiles)) {
            return mobiles.substring(0, 3) + "****" + mobiles.substring(7);
        } else return null;
    }

    public static boolean equals(String s1, String s2) {
        return Objects.equal(s1, s2);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    //替换空格，tab，制表符，换行符 以及全角空格等
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\u3000|\\s*|\t|\r|\n");// \u3000表示全角空格
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String formatValue(int value){
        DecimalFormat format = new DecimalFormat(",###");
        return format.format(value);
    }
}