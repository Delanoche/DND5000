package connorhenke.com.dnd5000;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwray.groupie.GroupAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EquipmentFragment extends Fragment {

    private GroupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_stats, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.stats_recycler);
        adapter = new GroupAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
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
                        adapter.add(new EquipmentItem(2, "2.0", "Squidly Daggers", true));
                        adapter.add(new EquipmentItem(1, "0.5", "Potion of Healing", false));
                        adapter.add(new EquipmentItem(100, "0.1", "Gold", false));
                    }
                });
        return view;
    }
}
