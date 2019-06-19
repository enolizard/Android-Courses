package by.yakivan.starbuzz.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import by.yakivan.starbuzz.Drink;
import by.yakivan.starbuzz.R;
import by.yakivan.starbuzz.Utils.Toaster;
import by.yakivan.starbuzz.database.DatabaseHelper;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";
    private ImageView photo;
    private TextView name;
    private TextView description;
    private CheckBox favorite;

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
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                name.setText(cursor.getString(0));
                description.setText(cursor.getString(1));
                photo.setImageResource(cursor.getInt(2));
                favorite.setChecked(cursor.getInt(3) == 1);
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toaster.toastLong(this, "Data unavailable");
        }
    }

    private void findActivityViews() {
        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        favorite = findViewById(R.id.favorite);
    }

    public void onFavoriteClicked(View view) {
        int drinkId = (int) getIntent().getExtras().get(EXTRA_DRINK_ID);
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", favorite.isChecked());

        SQLiteOpenHelper openHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = openHelper.getWritableDatabase();
            db.update("DRINK",
                    drinkValues,
                    "_id = ?",
                    new String[]{String.valueOf(drinkId)});
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toaster.toastLong(this, "Data unavailable");
        }
    }
}
