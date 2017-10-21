package connorhenke.com.dnd5000;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class LongTextItem extends Item<ViewHolder> {

    private String title;
    private String body;

    public LongTextItem(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView title = viewHolder.itemView.findViewById(R.id.list_long_text_title);
        TextView body = viewHolder.itemView.findViewById(R.id.list_long_text_body);

        title.setText(this.title);
        body.setText(this.body);
    }

    @Override
    public int getLayout() {
        return R.layout.list_long_text;
    }
}
