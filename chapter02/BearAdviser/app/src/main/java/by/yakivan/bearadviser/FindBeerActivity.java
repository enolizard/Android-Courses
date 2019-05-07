package by.yakivan.bearadviser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FindBeerActivity extends AppCompatActivity {
    private Spinner color;
    private TextView brands;
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
        color = findViewById(R.id.color);
        brands = findViewById(R.id.brands);
    }

    public void onClickFindBeer(View view) {
        String choose = String.valueOf(color.getSelectedItem());
        StringBuffer buffer = new StringBuffer();

        for (String s : expert.getBrands(choose)) {
            buffer.append(s).append('\n');
        }

        brands.setText(buffer.toString());
    }
}
