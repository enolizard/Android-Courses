package by.yakivan.odometer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final int PERMISSION_REQUEST_CODE = 698;
    private OdometerService odometer;
    private boolean bound = false;
    private TextView distanceView;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            OdometerService.OdometerBinder odometerBinder =
                    (OdometerService.OdometerBinder) service;
            odometer = odometerBinder.getOdometer();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findActivityViews();
        displayDistance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, OdometerService.PERMISSION_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, OdometerService.class);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{OdometerService.PERMISSION_LOCATION},
                    PERMISSION_REQUEST_CODE);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(conn);
            bound = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, OdometerService.class);
                    bindService(intent, conn, Context.BIND_AUTO_CREATE);
                } else {
                    Toast.makeText(this, "Please, turn on location permission", Toast.LENGTH_LONG)
                            .show();
                }
        }
    }

    private void findActivityViews() {
        distanceView = findViewById(R.id.distance);
    }

    private void displayDistance() {
        final TextView fDistanceView = distanceView;
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if (bound && odometer != null) {
                    distance = odometer.getDistance();
                }
                String distanceStr = String.format(
                        Locale.getDefault(),
                        "%1$,.2f miles",
                        distance);
                fDistanceView.setText(distanceStr);
                handler.postDelayed(this, 1000);
            }
        });
    }
}
