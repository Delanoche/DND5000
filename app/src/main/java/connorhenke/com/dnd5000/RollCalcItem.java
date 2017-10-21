package connorhenke.com.dnd5000;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class RollCalcItem extends Item<ViewHolder> {

    private RollResult result;

    public RollCalcItem(RollResult result) {
        this.result = result;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView calculation = (TextView) viewHolder.itemView.findViewById(R.id.roll_results);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (int i = 0; i < result.results.length; i++) {
            int color;
            if (result.results[i] == 1) {
                color = Color.RED;
            } else if (result.results[i] == result.diceType) {
                color = Color.GREEN;
            } else {
                color = Color.GRAY;
            }
            String num = "" + result.results[i];
            builder.append(num);
            builder.setSpan(new ForegroundColorSpan(color), builder.length() - num.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            if (i < result.results.length - 1) {
                builder.append(" + ");
            }
        }
        calculation.setText(builder);
    }

    @Override
    public int getLayout() {
        return R.layout.list_roll_calculation;
    }
}
