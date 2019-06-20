package by.yakivan.bitsandpizzas;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.PipedInputStream;

public class PizzaDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PIZZA_ID = "pizzaId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int pizzaId = getIntent().getIntExtra(EXTRA_PIZZA_ID, -1);
        TextView title = findViewById(R.id.pizza_text);
        String name = Pizza.pizzas[pizzaId].getName();
        title.setText(name);

        ImageView image = findViewById(R.id.pizza_image);
        Drawable drawable = ContextCompat.getDrawable(
                this,
                Pizza.pizzas[pizzaId].getImageResourseId());
        image.setImageDrawable(drawable);
        image.setContentDescription(name);
    }
}
