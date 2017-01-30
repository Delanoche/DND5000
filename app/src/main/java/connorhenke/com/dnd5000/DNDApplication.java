package connorhenke.com.dnd5000;

import android.app.Application;

import io.realm.Realm;

public class DNDApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(Character.class).findAll().size() == 0) {
            Character character = new Character();
            character.setName("Jeffy");
            realm.beginTransaction();
            realm.insert(character);
            realm.commitTransaction();
        }
    }
}
