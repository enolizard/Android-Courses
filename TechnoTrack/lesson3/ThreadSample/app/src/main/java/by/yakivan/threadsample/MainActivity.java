package by.yakivan.threadsample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView messageResult;
    private TextView messageObj;
    private TextView messageWhat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findActivityViews();

        new BackGroundThread(uiHandler).start();
    }

    private void findActivityViews() {
        messageResult = findViewById(R.id.message_result);
        messageObj = findViewById(R.id.message_obj);
        messageWhat = findViewById(R.id.message_what);
    }

    public void onSendMsg(View view) {
        Message msg = Message.obtain();
        msg.what = Integer.parseInt(messageWhat.getText().toString());
        msg.obj = messageObj.getText().toString();
        uiHandler.sendMessage(msg);
    }


    public Handler uiHandler = new Handler(new Handler.Callback() {
        private static final int SUCCESS = 1;
        private static final int FAIL = 2;
        private static final int CANCEL = 0;

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    messageResult.setText("SUCCESS: " + (String) msg.obj);
                    return true;
                case FAIL:
                    messageResult.setText("FAIL: " + (String) msg.obj);
                    return true;
                case CANCEL:
                    messageResult.setText("CANCEL: " + (String) msg.obj);
                    return true;
            }

            return false;
        }
    });
}
