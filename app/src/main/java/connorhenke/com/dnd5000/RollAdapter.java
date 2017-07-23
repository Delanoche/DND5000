package connorhenke.com.dnd5000;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public List<RollResult> rollResults;
    public LayoutInflater inflater;
    private ConstraintSet collapsed;
    private ConstraintSet expanded;

    public RollAdapter(List<RollResult> rollResults, Context context) {
        this.rollResults = rollResults;
        this.inflater = LayoutInflater.from(context);
        this.collapsed = new ConstraintSet();
        collapsed.clone(context, R.layout.list_roll);
        this.expanded = new ConstraintSet();
        expanded.clone(context, R.layout.list_roll_expanded);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RollHolder(inflater.inflate(R.layout.list_roll, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
    }

    public void add(RollResult result) {
        rollResults.add(result);
        this.notifyItemInserted(rollResults.size() - 1);
    }

    public int getSize() {
        return rollResults.size();
    }

    @Override
    public int getItemCount() {
        return rollResults.size();
    }

    static class RollHolder extends RecyclerView.ViewHolder {

        public TextView query;
        public TextView result;

        public RollHolder(View itemView) {
            super(itemView);

            query = (TextView) itemView.findViewById(R.id.roll_query);
            result = (TextView) itemView.findViewById(R.id.roll_result);
        }
    }

    static class ResultsHolder extends RecyclerView.ViewHolder {
        public TextView results;

        public ResultsHolder(View itemView) {
            super(itemView);
            results = (TextView) itemView.findViewById(R.id.roll_results);
        }
    }
}
