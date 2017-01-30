package connorhenke.com.dnd5000;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import connorhenke.com.dnd5000.databinding.ActivityCharacterListBinding;
import io.realm.Realm;
import io.realm.RealmResults;

public class CharacterListActivity extends AppCompatActivity {

    private ActivityCharacterListBinding binding;
    private List<Character> characterList;
    private CharacterAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_list);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Character> characters = realm.where(Character.class).findAll();
        characterList = realm.copyFromRealm(characters);
        adapter = new CharacterAdapter(this, characterList, new CharacterAdapter.CharacterOnClickListener() {
            @Override
            public void onClick(Character character) {

            }
        });
        binding.characterList.setLayoutManager(new LinearLayoutManager(this));
        binding.characterList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.characters, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Character> characters = realm.where(Character.class).findAll();
        characterList.clear();
        characterList.addAll(realm.copyFromRealm(characters));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_character) {
            startActivity(new Intent(this, AddCharacterActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
