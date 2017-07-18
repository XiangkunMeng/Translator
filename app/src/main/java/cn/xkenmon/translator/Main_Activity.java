package cn.xkenmon.translator;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * The MAIN Activity
 * Created by mxk94 on 2017/7/16.
 */

public class Main_Activity extends Activity implements View.OnClickListener {
    Translator translator;
    EditText editText;
    Handler uiHandler;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        String key = getString(R.string.key);
        String request_url = getString(R.string.request_url);
        translator = new Translator(key,request_url);
        editText = findViewById(R.id.keyword_input);
        textView = findViewById(R.id.rstView);
        uiHandler = new Handler();
        Button submit = findViewById(R.id.submit);

        editText.setOnKeyListener(new KeyWordListener(submit));
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Thread thread = new Thread(new ResultThread(translator, editText, this, uiHandler));
        thread.start();
    }

    void showResult(TransResult result) {
        LinearLayout pronLayout = findViewById(R.id.pronLayout);
        pronLayout.removeAllViewsInLayout();
        Map<String, URL> pron = result.getPron();
        for (final Map.Entry entry : pron.entrySet()) {
            Button pron_btn = new Button(this);
            pron_btn.setText(entry.getKey().toString());
            AssetManager mgr = getAssets();
            Typeface typeface = Typeface.createFromAsset(mgr,"fonts/lsansuni.ttf");
            pron_btn.setTypeface(typeface);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(10, 10, 10, 10);
            pron_btn.setBackground(getResources().getDrawable(R.drawable.corner_edittext));
            pron_btn.setPadding(5, 5, 5, 5);
            pron_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        new Thread(new Player(entry.getValue().toString(), uiHandler)).start();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            });
            pronLayout.addView(pron_btn, layoutParams);
        }

        textView.setVisibility(View.VISIBLE);
        textView.setText(result.toString());
    }
}
