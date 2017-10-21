package connorhenke.com.dnd5000;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

/**
 * Created by connorhenke on 10/21/17.
 */

public class InfoItem extends Item<ViewHolder> {

    private String info;
    private String title;

    public InfoItem(String info, String title) {
        this.info = info;
        this.title = title;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView info = viewHolder.itemView.findViewById(R.id.list_info_item_info);
        TextView title = viewHolder.itemView.findViewById(R.id.list_info_item_title);

        info.setText(this.info);
        title.setText(this.title);
    }

    @Override
    public int getLayout() {
        return R.layout.list_info_item;
    }
}
