package by.yakivan.joke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLog(View view) {
        runService(MainService.CommandType.LogMessage);
    }

    public void onClickMusic(View view) {
        runService(MainService.CommandType.Music);
    }

    public void onClickNotification(View view) {
        runService(MainService.CommandType.Notification);
    }

    private void runService(MainService.CommandType type) {
        Intent intent = new Intent(this, MainService.class);
        intent.putExtra(MainService.EXTRA_COMMAND_TYPE, type.toString());
        startService(intent);
    }
}
