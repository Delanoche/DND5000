package connorhenke.com.dnd5000;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumsAdapter extends RecyclerView.Adapter<NumsAdapter.NumHolder> {

    private List<String> nums;
    private LayoutInflater inflater;
    private int layout;
    private NumClickListener listener;

    public NumsAdapter(Context context, List<String> nums, @LayoutRes int layout, NumClickListener listener) {
        this.nums = nums;
        this.inflater = LayoutInflater.from(context);
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public NumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NumHolder(inflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final NumHolder holder, int position) {
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clicked(holder.text.getText().toString());
            }
        });
        holder.text.setText(nums.get(position));
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }

    interface NumClickListener {
        void clicked(String num);
    }

    class NumHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public NumHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
