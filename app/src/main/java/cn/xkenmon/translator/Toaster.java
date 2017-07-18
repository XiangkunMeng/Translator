package cn.xkenmon.translator;

import android.widget.Toast;

/**
 * Created by mxk94 on 2017/7/16.
 */

public class Toaster implements Runnable {
    String msg;

    Toaster(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
