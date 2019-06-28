package by.yakivan.threadsample;

import android.os.Handler;
import android.os.Message;

public class BackGroundThread extends Thread {
    private Handler uiHandler;


    public BackGroundThread(Handler uiHandler) {
        this.uiHandler = uiHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = Message.obtain();
            msg.what = 2;
            msg.obj = "Message from background!!";
            uiHandler.sendMessage(msg);
        }
    }
}

