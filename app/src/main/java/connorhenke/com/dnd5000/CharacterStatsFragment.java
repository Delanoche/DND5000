package connorhenke.com.dnd5000;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwray.groupie.GroupAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CharacterStatsFragment extends Fragment {

    private GroupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_stats, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.stats_recycler);
        adapter = new GroupAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position == 0) {
//                    return 3;
//                }
//                return 1;
//            }
//        });
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);

        int id = getActivity().getIntent().getIntExtra("id", 0);
        AppDatabase db = ((DNDApplication) getActivity().getApplication()).getDb();

        Disposable disposable = db.characterDao().get(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Character>() {
                    @Override
                    public void accept(Character character) throws Exception {
                        adapter.add(new InfoItem("" + character.getAc(), "Armor Class"));
                        adapter.add(new InfoItem("+1", "Initiative"));
                        adapter.add(new InfoItem("" + character.getSpeed() + " feet", "Speed"));
                        adapter.add(new AttributeItem(getModifier(character.getStrength()), "Strength", "" + character.getStrength()));
                        adapter.add(new AttributeItem(getModifier(character.getDex()), "Dexterity", "" + character.getDex()));
                        adapter.add(new AttributeItem(getModifier(character.getConstitution()), "Constitution", "" + character.getConstitution()));
                        adapter.add(new AttributeItem(getModifier(character.getIntelligence()), "Intelligence", "" + character.getIntelligence()));
                        adapter.add(new AttributeItem(getModifier(character.getWisdom()), "Wisdom", "" + character.getWisdom()));
                        adapter.add(new AttributeItem(getModifier(character.getCharisma()), "Charisma", "" + character.getCharisma()));
                    }
                });
        return view;
    }

    private String getModifier(int total) {
        total = total - 10;
        total = total / 2;
        StringBuilder builder = new StringBuilder();
        if (total < 0) {
            builder.append("-");
            total = total * -1;
        } else {
            builder.append("+");
        }
        builder.append(total);
        return builder.toString();
    }
}
