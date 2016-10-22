package connorhenke.com.dnd5000;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.ViewHolder> {

    private List<Monster> monsterList;
    private MonsterClickListener listener;

    public MonsterAdapter(List<Monster> monsterList, MonsterClickListener listener) {
        this.monsterList = monsterList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_monster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {
        final int position = holder.getAdapterPosition();
        Monster monster = monsterList.get(position);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(monsterList.get(position));
            }
        });
        holder.name.setText(WordUtils.capitalize(monster.getName()));
        float scale = holder.itemView.getContext().getResources().getDisplayMetrics().density;
        int dp16 = (int) (16 * scale + 0.5f);
        int dp8 = (int) (8 * scale + 0.5f);
        int dp4 = (int) (4 * scale + 0.5f);
        if (position == 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(dp16, dp16, dp16, dp4);
            holder.card.setLayoutParams(layoutParams);
        } else if (position == monsterList.size() - 1) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(dp16, dp4, dp16, dp16);
            holder.card.setLayoutParams(layoutParams);
        } else {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(dp16, dp4, dp16, dp4);
            holder.card.setLayoutParams(layoutParams);
        }
    }

    public void swap(List<Monster> list) {
        this.monsterList = list;
    }

    @Override
    public int getItemCount() {
        return monsterList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.monster_card);
            name = (TextView) itemView.findViewById(R.id.monster_name);
        }
    }

    static interface MonsterClickListener {
        void onClick(Monster monster);
    }
}