package connorhenke.com.dnd5000;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

/**
 * Created by connorhenke on 10/21/17.
 */

public class BigItem extends Item<ViewHolder> {

    private String title;

    public BigItem(String title) {
        this.title = title;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView title = viewHolder.itemView.findViewById(R.id.list_big_item_title);
        title.setText(this.title);
    }

    @Override
    public int getLayout() {
        return R.layout.list_big_item;
    }
}
