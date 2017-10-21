package connorhenke.com.dnd5000;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class AttributeItem extends Item<ViewHolder> {

    private String modifier;
    private String title;
    private String total;

    public AttributeItem(String modifier, String title, String total) {
        this.modifier = modifier;
        this.title = title;
        this.total = total;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView modifier = viewHolder.itemView.findViewById(R.id.list_attribute_modifier);
        TextView title = viewHolder.itemView.findViewById(R.id.list_attribute_title);
        TextView total = viewHolder.itemView.findViewById(R.id.list_attribute_total);

        modifier.setText(this.modifier);
        title.setText(this.title);
        total.setText(this.total);
    }

    @Override
    public int getLayout() {
        return R.layout.list_attribute_item;
    }
}
