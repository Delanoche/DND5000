package connorhenke.com.dnd5000;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CharactersActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Character> characterList;
    private CharacterAdapter adapter;
    private RecyclerView charactersRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        charactersRecycler = findViewById(R.id.characters_recycler);
        charactersRecycler.setItemAnimator(new DefaultItemAnimator());

        db = ((DNDApplication)getApplication()).getDb();
        characterList = new ArrayList<>();
        adapter = new CharacterAdapter(characterList, this, new CharacterAdapter.CharacterClickListener() {
            @Override
            public void clicked(Character character) {
                Intent intent = new Intent(CharactersActivity.this, CreateCharacterActivity.class);
                intent.putExtra("id", character.getId());
                startActivity(intent);
            }
        });
        adapter.setHasStableIds(true);
        charactersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        charactersRecycler.setAdapter(adapter);


        Disposable disposable = db.characterDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Character>>() {
                    @Override
                    public void accept(List<Character> characters) throws Exception {
                        characterList.clear();
                        characterList.addAll(characters);
                        adapter.notifyItemRangeInserted(0, characters.size());
                    }
                });
    }

    private void insert(int size) {
        if (size < 1) {
            Single.just(db.characterDao())
                    .subscribeOn(Schedulers.io())
                    .map(new Function<CharacterDao, Object>() {
                        @Override
                        public Object apply(@NonNull CharacterDao characterDao) throws Exception {
                            Character test = new Character();
                            test.setName("Davy Jones");
                            test.setLevel(7);
                            test.setAc(15);
                            test.setAlignment("Neutral Evil");
                            test.setBackground("Locker");
                            test.setCharisma(18);
                            test.setClazz("Spooky Pirate");
                            test.setConstitution(15);
                            test.setDex(10);
                            test.setHpCurrent(190);
                            test.setHpMax(190);
                            test.setIntelligence(18);
                            test.setRace("Squid Man");
                            test.setSpeed(45);
                            test.setStrength(19);
                            test.setWisdom(17);
                            characterDao.insertAll(test);
                            return Integer.MIN_VALUE;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.characters_add) {
            startActivity(new Intent(this, CreateCharacterActivity.class));
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.characters_menu, menu);
        return true;
    }
}
