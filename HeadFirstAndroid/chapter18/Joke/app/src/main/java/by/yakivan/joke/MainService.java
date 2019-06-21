package by.yakivan.joke;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

public class MainService extends IntentService {
    public static final String SERVICE_NAME = "MainService";
    public static final String EXTRA_COMMAND_TYPE = "CommandType";

    public MainService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        CommandType command = CommandType.valueOf(intent.getStringExtra(EXTRA_COMMAND_TYPE));
        switch (command) {
            case Music:
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gachibass);
                mediaPlayer.start();
                break;
            case LogMessage:
                showText(getResources().getString(R.string.response));
                break;
            case Notification:
                break;
        }
    }

    private void showText(String text) {
        Log.e(SERVICE_NAME, "This a message: " + text);
    }

    public enum CommandType {
        Music,
        LogMessage,
        Notification
    }
}
