package connorhenke.com.dnd5000;

import android.support.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

/**
 * Created by connorhenke on 10/21/17.
 */

public class EquipmentItem extends Item<ViewHolder> {

    private int quantity;
    private String weight;
    private String name;
    private boolean equipped;

    public EquipmentItem(int quantity, String weight, String name, boolean equipped) {
        this.quantity = quantity;
        this.weight = weight;
        this.name = name;
        this.equipped = equipped;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView quantity = viewHolder.itemView.findViewById(R.id.list_equipment_quantity);
        TextView name = viewHolder.itemView.findViewById(R.id.list_equipment_name);
        TextView weight = viewHolder.itemView.findViewById(R.id.list_equipment_weight);
        CheckBox equipped = viewHolder.itemView.findViewById(R.id.list_equipment_equipped);

        quantity.setText("" + this.quantity);
        name.setText(this.name);
        weight.setText(this.weight);
        equipped.setChecked(this.equipped);
    }

    @Override
    public int getLayout() {
        return R.layout.list_equipment_item;
    }
}
