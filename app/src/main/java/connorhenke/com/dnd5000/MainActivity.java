package connorhenke.com.dnd5000;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connorhenke.com.dnd5000.databinding.ActivityMainBinding;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private List<Spell> spellList;
    private SpellAdapter adapter;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private EditText search;
    private boolean isSearchVisible;

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
                final String text = editable.toString();
                Observable.from(spellList)
                        .subscribeOn(Schedulers.computation())
                        .filter(new Func1<Spell, Boolean>() {
                            @Override
                            public Boolean call(Spell spell) {
                                return spell.getName().contains(text) || spell.getDescription().contains(text) || spell.getSchool().contains(text);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(new Action1<List<Spell>>() {
                            @Override
                            public void call(List<Spell> spells) {
                                adapter.swap(spells);
                                adapter.notifyDataSetChanged();
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
        JSONArray array;
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            //returns the json object
            array = new JSONArray(responseStrBuilder.toString());
            for (int i = 0; i < array.length(); i++) {
                Spell spell = Spell.fromJson(array.getJSONObject(i));
                spellList.add(spell);
            }
            Collections.sort(spellList, Sorter.alphabetically());
            adapter.notifyDataSetChanged();

        } catch (IOException|JSONException e) {
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
        } else if (id == R.id.spell_search) {
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
