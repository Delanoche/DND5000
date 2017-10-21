package connorhenke.com.dnd5000;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connorhenke.com.dnd5000.databinding.ActivityMainBinding;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private List<Spell> spellList;
    private SpellAdapter adapter;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private EditText search;
    private boolean isSearchVisible;
    private RecyclerView tagLayout;
    private TagAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search = (EditText) toolbar.findViewById(R.id.search_edit_text);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (hasFocus) {
                    bottomSheetBehavior.setState(bottomSheetBehavior.STATE_HIDDEN);
                    methodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String text = editable.toString().toLowerCase();
                Observable.fromIterable(spellList)
                        .subscribeOn(Schedulers.computation())
                        .filter(new Predicate<Spell>() {
                            @Override
                            public boolean test(@NonNull Spell spell) throws Exception {
                                return spell.getName().contains(text) || spell.getDescription().contains(text) || spell.getSchool().contains(text);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(new SingleObserver<List<Spell>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Spell> spells) {
                                adapter.swap(spells);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.spell_list);
        bottomSheet = findViewById(R.id.bottom_sheet);
        spellList = new ArrayList<>();
        adapter = new SpellAdapter(spellList, new SpellAdapter.SpellClickListener() {
            @Override
            public void onClick(Spell spell) {
                binding.setSpell(spell);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                recyclerView.requestFocus();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        InputStream inputStream = getResources().openRawResource(R.raw.spells);
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            //returns the json object
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Spell>>(){}.getType();
            spellList = gson.fromJson(responseStrBuilder.toString(), collectionType);
            Collections.sort(spellList, SpellSorter.alphabetically());

            adapter.swap(spellList);
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.content_search) {
            if (!isSearchVisible) {
                search.setVisibility(View.VISIBLE);
                setTitle("");
                item.setIcon(getDrawable(R.drawable.rotate));
                ((AnimatedVectorDrawable)item.getIcon()).start();
//                item.setIcon(R.drawable.ic_clear_white_24dp);
                isSearchVisible = true;
                search.requestFocus();
            } else {
                search.setVisibility(View.GONE);
                search.getEditableText().clear();
                setTitle(R.string.app_name);
                item.setIcon(getDrawable(R.drawable.rotate_reverse));
                ((AnimatedVectorDrawable)item.getIcon()).start();
//                item.setIcon(R.drawable.ic_search_white_24dp);
                isSearchVisible = false;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_spells) {
            startActivity(new Intent(this, DiceActivity.class));
        } else if (id == R.id.nav_calculator) {
            startActivity(new Intent(this, CalculatorActivity.class));
        } else if (id == R.id.nav_monsters) {
            startActivity(new Intent(this, MonstersActivity.class));
        } else if (id == R.id.nav_characters) {
            startActivity(new Intent(this, CharactersActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
