package com.example.base;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by lixiaoming on 2017/8/3.
 * <p>
 * 软键盘弹出和隐藏监听
 */

public class SoftPanChangeUtil {

    private static final int MIN_KEYBOARD_HEIGHT_PX = 150; //软键盘最小高度

    /**
     * decorView:getWindow.getDecorView();
     * */
    public static void SoftPanChange(final View decorView, final SoftPanChangedListener listener) {
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private final Rect windowVisibleDisplayFrame = new Rect();
            private int lastVisibleDecorViewHeight;

            @Override
            public void onGlobalLayout() {
                //获取decorview的可见范围
                decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame);
                final int visibleDecorViewHeight = windowVisibleDisplayFrame.height();

                if (lastVisibleDecorViewHeight != 0) {
                    if (lastVisibleDecorViewHeight > visibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX) {  //弹出软键盘
                        // 计算当前软键盘高度(这个高度包含来全屏时navigation bar的高度).
                        int currentKeyboardHeight = decorView.getHeight() - windowVisibleDisplayFrame.bottom;
                        if (listener != null) {
                            listener.showSoftPan(true);
                        }
                    } else if (lastVisibleDecorViewHeight + MIN_KEYBOARD_HEIGHT_PX < visibleDecorViewHeight) {//隐藏软键盘
                        if (listener != null) {
                            listener.showSoftPan(false);
                        }
                    }
                }
                //保存decorview高度
                lastVisibleDecorViewHeight = visibleDecorViewHeight;
            }
        });
    }

    public interface SoftPanChangedListener {
        void showSoftPan(boolean isShowed);
    }

    public static void showSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }
}
