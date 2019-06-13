package by.yakivan.starbuzz.activities;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import by.yakivan.starbuzz.Drink;
import by.yakivan.starbuzz.R;
import by.yakivan.starbuzz.database.DatabaseHelper;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";
    private ImageView photo;
    private TextView name;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        findActivityViews();

        int drinkId = getIntent().getIntExtra(EXTRA_DRINK_ID, -1);
        if (drinkId == -1) finish();

        SQLiteOpenHelper openHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = openHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    "DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId + 1)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                name.setText(cursor.getString(0));
                description.setText(cursor.getString(1));
                photo.setImageResource(cursor.getInt(2));
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Database unavailable!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void findActivityViews() {
        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
    }
}
