package connorhenke.com.dnd5000;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {

    private LayoutInflater inflater;
    private List<Character> characters;
    private CharacterClickListener listener;

    public CharacterAdapter(List<Character> characters, Context context, CharacterClickListener listener) {
        this.characters = characters;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CharacterHolder(inflater.inflate(R.layout.list_character, parent, false));
    }

    @Override
    public void onBindViewHolder(CharacterHolder holder, int position) {
        final Character character = characters.get(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clicked(character);
            }
        });
        holder.name.setText(characters.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public long getItemId(int position) {
        return characters.get(position).getId();
    }

    interface CharacterClickListener {
        void clicked(Character character);
    }

    static class CharacterHolder extends RecyclerView.ViewHolder {

        TextView name;

        public CharacterHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.characters_name);
        }
    }
}
