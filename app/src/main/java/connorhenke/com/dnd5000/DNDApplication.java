package connorhenke.com.dnd5000;

import android.app.Application;
import android.arch.persistence.room.Room;

public class DNDApplication extends Application {

    private AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dnd").build();
    }

    public AppDatabase getDb() {
        return db;
    }
}
