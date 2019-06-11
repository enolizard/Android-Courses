package by.yakivan.starbuzz.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import by.yakivan.starbuzz.Drink;
import by.yakivan.starbuzz.R;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = getIntent().getIntExtra(EXTRA_DRINK_ID, -1);

        ImageView photo = findViewById(R.id.photo);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);

        if (drinkId == -1) {
            finish();
        } else {
            Drink drink = Drink.drinks[drinkId];
            photo.setImageResource(drink.getImageResourseId());
            name.setText(drink.getName());
            description.setText(drink.getDescription());
        }
    }
}
