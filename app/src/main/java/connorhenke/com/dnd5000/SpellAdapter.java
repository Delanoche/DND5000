package connorhenke.com.dnd5000;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.ViewHolder> {

    private List<Spell> spellList;
    private SpellClickListener listener;

    public SpellAdapter(List<Spell> spellList, SpellClickListener listener) {
        this.spellList = spellList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_spell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int pos) {
        final int position = holder.getAdapterPosition();
        Spell spell = spellList.get(position);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(spellList.get(position));
            }
        });
        holder.name.setText(spell.getName());
//        float scale = holder.itemView.getContext().getResources().getDisplayMetrics().density;
//        int dp16 = (int) (16*scale + 0.5f);
//        int dp8 = (int) (8*scale + 0.5f);
//        if (position == spellList.size() - 1) {
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(dp16, dp8, dp16, dp16);
//            holder.card.setLayoutParams(layoutParams);
//        } else if (position == 0) {
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(dp16, dp16, dp16, dp8);
//            holder.card.setLayoutParams(layoutParams);
//        }
    }

    @Override
    public int getItemCount() {
        return spellList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.spell_card);
            name = (TextView) itemView.findViewById(R.id.spell_name);
        }
    }

    static interface SpellClickListener {
        void onClick(Spell spell);
    }
}
