package cn.xkenmon.translator;

import android.app.Application;
import android.content.Context;

/**
 * Created by mxk94 on 2017/7/16.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
