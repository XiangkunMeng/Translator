package cn.xkenmon.translator;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * Enter Key Listener
 * Created by mxk94 on 2017/7/16.
 */

public class KeyWordListener implements View.OnKeyListener {
    private Button button;

    KeyWordListener(Button button) {
        this.button = button;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER) {
            button.callOnClick();
            return true;
        }
        return false;
    }
}
