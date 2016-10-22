package connorhenke.com.dnd5000;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connorhenke.com.dnd5000.databinding.ActivityMainBinding;
import connorhenke.com.dnd5000.databinding.ActivityMonstersBinding;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MonstersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private List<Monster> monsterList;
    private MonsterAdapter adapter;
    private EditText search;
    private boolean isSearchVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMonstersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_monsters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search = (EditText) toolbar.findViewById(R.id.search_edit_text);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (hasFocus) {
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
                Observable.from(monsterList)
                        .subscribeOn(Schedulers.computation())
                        .filter(new Func1<Monster, Boolean>() {
                            @Override
                            public Boolean call(Monster monster) {
                                return monster.getName().toLowerCase().contains(text);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(new Action1<List<Monster>>() {
                            @Override
                            public void call(List<Monster> monsters) {
                                adapter.swap(monsters);
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.monster_list);
        monsterList = new ArrayList<>();
        adapter = new MonsterAdapter(monsterList, new MonsterAdapter.MonsterClickListener() {
            @Override
            public void onClick(Monster monster) {
//                binding.setMonster(monster);
                recyclerView.requestFocus();

                Intent intent = new Intent(MonstersActivity.this, MonsterDetailActivity.class);
                intent.putExtra("monster", monster);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        InputStream inputStream = getResources().openRawResource(R.raw.monsters);
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            //returns the json object
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Monster>>(){}.getType();
            monsterList = gson.fromJson(responseStrBuilder.toString(), collectionType);
            Collections.sort(monsterList, MonsterSorter.alphabetically());

            adapter.swap(monsterList);
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
                item.setIcon(R.drawable.ic_clear_white_24dp);
                isSearchVisible = true;
                search.requestFocus();
            } else {
                search.setVisibility(View.GONE);
                search.getEditableText().clear();
                setTitle(R.string.app_name);
                item.setIcon(R.drawable.ic_search_white_24dp);
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

        Intent intent = null;
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_monsters) {
            intent = new Intent(this, MonstersActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
