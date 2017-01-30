package connorhenke.com.dnd5000;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import connorhenke.com.dnd5000.databinding.ActivityAddCharacterBinding;
import io.realm.Realm;

public class AddCharacterActivity extends AppCompatActivity {

    private Character character;
    private ActivityAddCharacterBinding binding;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        character = new Character();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_character);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    private void setCharacter() {
        character.setName(binding.characterSheetName.getText().toString());
        character.setmClass(binding.characterSheetClass.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_character) {
            setCharacter();
            realm.beginTransaction();
            realm.insert(character);
            realm.commitTransaction();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
