package cm.it.secret.gasmyr.toolkit.app;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Created by gasmyr.mougang on 5/31/17.
 */

public class SpeechService extends Service implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private String message;
    private boolean isCallMessage;
    private boolean isInit;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(getApplicationContext(), this);
        handler = new Handler();
    }

    @Override
    public int onStartCommand(@NonNull Intent intent, int flags, int startId) {
        try {
            handler.removeCallbacksAndMessages(null);
            Bundle data = intent.getExtras();
            message = data.getString(ToolkitConstants.SPEAKER_SERVICE_MESSAGE, message);
            isCallMessage = data.getBoolean(ToolkitConstants.SPEAKER_SERVICE_TARGET, false);
            if (isInit) {
                speak(isCallMessage);
            }
            waitAwhile();

        } catch (Exception e) {
            return SpeechService.START_NOT_STICKY;
        }
        return SpeechService.START_NOT_STICKY;
    }

    private void waitAwhile() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        }, 15 * 1000);
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setSpeechRate(0.6f);
            int result = tts.setLanguage(Locale.getDefault());
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                speak(isCallMessage);
                isInit = true;
            }
        }
    }

    private void speak(boolean isCallMessage) {
        if (tts != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts.speak(message, TextToSpeech.QUEUE_ADD, null, null);
                if (isCallMessage) {
                    tts.speak(message, TextToSpeech.QUEUE_ADD, null, null);
                }
            } else {
                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                if (isCallMessage) {
                    tts.speak(message, TextToSpeech.QUEUE_ADD, null);
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
