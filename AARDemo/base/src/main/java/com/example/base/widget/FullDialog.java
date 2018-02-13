package com.example.base.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.base.R;

/**
 * Created by lixiaoming on 2017/11/22.
 */

public abstract class FullDialog {

    protected Context context;

    protected AlertDialog dialog;

    public FullDialog(Context context) {
        this.context = context;
        dialog = new AlertDialog.Builder(context, getStyle()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        initDialogWindow(dialog);
    }

    private void initDialogWindow(AlertDialog dialog) {
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        window.setContentView(getLayoutResID());
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.telDialogAnim); // 添加动画

        setViews(dialog);
    }

    protected abstract int getStyle();

    protected abstract int getLayoutResID();

    protected abstract void setViews(AlertDialog view);

}
