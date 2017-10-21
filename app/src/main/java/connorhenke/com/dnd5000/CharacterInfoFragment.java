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
import com.xwray.groupie.ViewHolder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CharacterInfoFragment extends Fragment {

    private GroupAdapter<ViewHolder> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_info, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.character_recycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else if (position == 5 || position == 6) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new GroupAdapter<>();
        recyclerView.setAdapter(adapter);

        int id = getActivity().getIntent().getIntExtra("id", 0);
        AppDatabase db = ((DNDApplication) getActivity().getApplication()).getDb();

        Disposable disposable = db.characterDao().get(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Character>() {
                    @Override
                    public void accept(Character character) throws Exception {
                        adapter.add(new BigItem(character.getName()));
                        adapter.add(new InfoItem(character.getClazz() + " " + character.getLevel(), "Class & Level"));
                        adapter.add(new InfoItem(character.getBackground(), "Background"));
                        adapter.add(new InfoItem(character.getRace(), "Race"));
                        adapter.add(new InfoItem(character.getAlignment(), "Alignment"));
                        adapter.add(new LongTextItem("Motivations", "He thinks his locker is quite neat"));
                        adapter.add(new LongTextItem("Story", "Davy Jones, a human and a great sailor, fell in love with Calypso, a sea goddess. She entrusted him with the task of ferrying the souls of those who died at sea to the next world. Calypso gave him the Flying Dutchman to accomplish this task. She swore that after ten years, she would meet him and they would spend one day together before he returned to his duties. However, when Jones returned to shore after ten years, Calypso failed to appear. Believing Calypso had betrayed him, a heartbroken and enraged Davy Jones turned the Pirate Brethren against her, saying that if she were removed from the world, they would be able to claim the seas for themselves. They assembled in the First Brethren Court and Jones taught them how to imprison her into her human form."));
                    }
                });
        return view;
    }
}
