package by.yakivan.threadsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findActivityViews();

        new Thread(new WorkingClass(true, message)).start();
    }

    private void findActivityViews() {
        message = findViewById(R.id.message);
    }
}
