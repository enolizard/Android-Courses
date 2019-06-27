package by.yakivan.threadsample;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class WorkingClass implements Runnable {

    public static final int SUCCESS = 1;
    public static final int FAIL = 2;
    private boolean dummyResult;
    private TextView message;

    private Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    message.setText("SUCCESS");
                    return true;
                case FAIL:
                    message.setText((String) msg.obj);
                    return true;
            }

            return false;
        }
    });

    public WorkingClass(boolean dummyResult, TextView message) {
        this.dummyResult = dummyResult;
        this.message = message;
    }

    @Override
    public void run() {
        if (dummyResult) {
            uiHandler.sendEmptyMessage(SUCCESS);
        } else {
            Message msg = Message.obtain();
            msg.what = FAIL;
            msg.obj = "An error occurred";
            uiHandler.sendMessage(msg);
        }
    }
}
