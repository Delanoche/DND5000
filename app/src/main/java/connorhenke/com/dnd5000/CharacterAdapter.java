package connorhenke.com.dnd5000;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Character> characters;
    private CharacterOnClickListener listener;

    public CharacterAdapter(Context context, List<Character> characters, CharacterOnClickListener listener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.characters = characters;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        holder.name.setText(characters.get(pos).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(characters.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public interface CharacterOnClickListener {
        void onClick(Character character);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.character_name);
        }
    }
}
