package by.enolizard.exampletwo.choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import by.enolizard.exampletwo.R;
import by.enolizard.exampletwo.activity.SingleActivity;
import by.enolizard.exampletwo.mvp.UsersActivity;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        findViewById(R.id.activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseActivity.this, SingleActivity.class));
            }
        });

        findViewById(R.id.mvp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseActivity.this, UsersActivity.class));
            }
        });
    }
}
