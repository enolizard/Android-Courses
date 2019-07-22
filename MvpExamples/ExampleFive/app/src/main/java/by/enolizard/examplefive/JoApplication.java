package by.enolizard.examplefive;

import android.app.Application;

import by.enolizard.examplefive.models.AppDatabase;

public class JoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        buildAppDatabase();
    }

    private void buildAppDatabase() {
        AppDatabase.getAppDatabase(this);
    }
}
