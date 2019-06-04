package by.yakivan.workout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchFragment extends Fragment implements View.OnClickListener {
    public static final String EXTRA_SECONDS = "seconds";
    public static final String EXTRA_RUNNING = "running";
    public static final String EXTRA_WAS_RUNNING = "wasRunning";

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    public StopwatchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(EXTRA_SECONDS);
            running = savedInstanceState.getBoolean(EXTRA_RUNNING);
            wasRunning = savedInstanceState.getBoolean(EXTRA_WAS_RUNNING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);
        layout.findViewById(R.id.start_button).setOnClickListener(this);
        layout.findViewById(R.id.stop_button).setOnClickListener(this);
        layout.findViewById(R.id.reset_button).setOnClickListener(this);

        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        running = wasRunning;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(EXTRA_SECONDS, seconds);
        savedInstanceState.putBoolean(EXTRA_RUNNING, running);
        savedInstanceState.putBoolean(EXTRA_WAS_RUNNING, wasRunning);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                onClickStart();
                break;
            case R.id.stop_button:
                onClickStop();
                break;
            case R.id.reset_button:
                onClickReset();
        }
    }

    private void onClickStart() {
        running = true;
    }

    private void onClickStop() {
        running = false;
    }

    private void onClickReset() {
        running = false;
        seconds = 0;
    }

    private void runTimer(View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }
}
