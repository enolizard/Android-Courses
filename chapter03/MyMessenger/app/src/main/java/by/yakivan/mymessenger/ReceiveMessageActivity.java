package by.yakivan.mymessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        TextView viewMessages = findViewById(R.id.receive_message);
        viewMessages.setText(getIntent().getStringExtra(EXTRA_MESSAGE));
    }
}
