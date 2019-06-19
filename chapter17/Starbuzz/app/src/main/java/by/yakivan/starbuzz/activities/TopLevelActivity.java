package by.yakivan.starbuzz.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import by.yakivan.starbuzz.R;
import by.yakivan.starbuzz.Utils.Toaster;
import by.yakivan.starbuzz.database.DatabaseHelper;

public class TopLevelActivity extends AppCompatActivity {
    private ListView listOptions;
    private ListView listFavorites;

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        findActivityViews();
        setupOptionsListView();
        setupFavoritesListView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter cursorAdapter = (CursorAdapter) listFavorites.getAdapter();
            cursorAdapter.changeCursor(cursor);
        } catch (SQLException e) {
            Toaster.toastLong(this, "Database unavailable");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    private void findActivityViews() {
        listOptions = findViewById(R.id.list_options);
        listFavorites = findViewById(R.id.list_favorites);
    }

    private void setupOptionsListView() {
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        listOptions.setOnItemClickListener(itemClickListener);
    }

    private void setupFavoritesListView() {
        SQLiteOpenHelper openHelper = new DatabaseHelper(this);
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLException e) {
            Toaster.toastLong(this, "Database unavailable");
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, (int) id);
                startActivity(intent);
            }
        });
    }
}
