package cn.xkenmon.translator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Get Result (net used Thread)
 * Created by mxk94 on 2017/7/16.
 */

public class ResultThread implements Runnable {
    private Main_Activity main_activity;
    private Handler uiHandler;
    Translator translator;
    private EditText editText;
    private TransResult result = new TransResult();

    ResultThread(Translator translator, EditText editText, Main_Activity main_activity, Handler uiHandler) {
        this.main_activity = main_activity;
        this.uiHandler = uiHandler;
        this.translator = translator;
        this.editText = editText;
    }

    @Override
    public void run() {

        try {
            result = translator.translate(editText.getText().toString());
            Log.d("info",result.getPron().toString());
            Log.d("info", result.toString());
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    main_activity.showResult(result);
                }
            });

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            uiHandler.post(new Toaster("Parser Configuration Exception"));
        } catch (IOException e) {
            e.printStackTrace();
            ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo == null || !mNetworkInfo.isAvailable())
                uiHandler.post(new Toaster("网络连接异常"));
            else
                uiHandler.post(new Toaster("IO异常"));
        } catch (SAXException e) {
            e.printStackTrace();
            uiHandler.post(new Toaster("SAX Exception"));
        }

    }
}
