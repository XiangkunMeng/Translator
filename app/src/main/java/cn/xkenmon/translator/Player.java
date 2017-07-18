package cn.xkenmon.translator;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Player pron
 * Created by mxk94 on 2017/7/17.
 */

public class Player implements Runnable {
    private String url;
    private Handler uiHandler;

    Player(String url,Handler handler) throws MalformedURLException {
        this.url = url;
        this.uiHandler = handler;
    }

    @Override
    public void run() {
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(url);
            player.prepare();
            player.start();
//            player.release();
        } catch (IOException e) {
            e.printStackTrace();
            uiHandler.post(new Toaster("无法获取音频资源"));
        }
    }
}
