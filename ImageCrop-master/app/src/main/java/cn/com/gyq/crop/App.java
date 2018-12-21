package cn.com.gyq.crop;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cn.com.gyq.crop.util.ScreenInfo;


/**
 * epaper app
 * Created by Administrator on 2018\8\15 0015.
 */

public class App extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ScreenInfo.init(this);

    }

    public static Context getAppContext() {
        return mContext;
    }


}
