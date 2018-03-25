package com.ldrtech.music.utils;

/**
 * 执行parse方法
 * Created by xiaoh on 2018/3/25.
 */

public class ParseUtils {
    public static long parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
