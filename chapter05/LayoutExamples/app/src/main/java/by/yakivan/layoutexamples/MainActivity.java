package by.yakivan.layoutexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDuckLayout(View view) {
        Toast.makeText(this, "Hello, I am a duck.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DuckActivity.class);
        startActivity(intent);
    }

    public void onTeaLayout(View view) {
        Toast.makeText(this, "Please, Choose drink.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TeaActivity.class);
        startActivity(intent);

    }
}
