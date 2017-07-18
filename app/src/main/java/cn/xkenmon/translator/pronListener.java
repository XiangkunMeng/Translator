package cn.xkenmon.translator;

import android.os.Handler;
import android.view.View;

import java.net.MalformedURLException;

/**
 * !!unused!!
 * Created by mxk94 on 2017/7/17.
 */

public class pronListener implements View.OnClickListener {
    private String url;
    private Handler uiHandler;

    pronListener(String url, Handler uiHandler) {
        this.uiHandler = uiHandler;
        this.url = url;
    }

    @Override
    public void onClick(View view) {
        try {
            new Thread(new Player(url,uiHandler)).run();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
