package cn.com.gyq.crop.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import cn.com.gyq.crop.App;

/**
 * 得到屏幕宽高密度等
 *
 * @author csw
 */
public class ScreenInfo {

    /**
     * 屏幕宽度（像素）
     */
    public static int width;
    /**
     * 屏幕高度（像素）
     */
    public static int height;
    /**
     * 屏幕密度（0.75 / 1.0 / 1.5）
     */
    public static float density;
    /**
     * 屏幕密度DPI（120 / 160 / 240）
     */
    public static int densityDpi;
    /**
     * 可定义为滑动的最短距离
     */
    public static int minSpanForSlop;

    public static void init(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        width = metric.widthPixels;
        height = metric.heightPixels;
        density = metric.density;
        densityDpi = metric.densityDpi;
        minSpanForSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        return (int) (dpValue * density + 0.5f * (dpValue >= 0 ? 1 : -1));
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / density + 0.5f * (pxValue >= 0 ? 1 : -1));
    }


    /**
     * 当前的屏幕是否竖屏
     *
     * @return true 竖屏状态 else 旋转了
     */
    public static boolean currIsLandscape() {
        WindowManager wm = (WindowManager) App.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getRotation() == Surface.ROTATION_0;
    }

}
