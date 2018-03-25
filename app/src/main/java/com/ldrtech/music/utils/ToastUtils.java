package com.ldrtech.music.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast消息提示工具类
 * Created by zjs on 2018/3/25.
 */

public class ToastUtils {
    private static Context sContext;
    private static Toast sToast;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static void show(int resId) {
        show(sContext.getString(resId));
    }

    public static void show(String text) {
        if (sToast == null) {
            sToast = Toast.makeText(sContext, text, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(text);
        }
        sToast.show();
    }
}
