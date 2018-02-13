package com.example.base.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lixiaoming on 2018/2/13.
 */

public class ToastUtils {
    public static void showToast(Context context) {
        Toast.makeText(context, "hello world!!", Toast.LENGTH_LONG).show();
    }
}
