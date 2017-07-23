package connorhenke.com.dnd5000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RollItem extends Item<ViewHolder> {

    public RollResult result;

    public RollItem(RollResult result) {
        this.result = result;
    }

    @Override
    public boolean isClickable() {
        return true;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView query = (TextView) viewHolder.itemView.findViewById(R.id.roll_query);
        TextView resultText = (TextView) viewHolder.itemView.findViewById(R.id.roll_result);
        query.setText("" + result.diceNum + "d" + result.diceType);
        int color;
        boolean green = result.result == result.diceNum * result.diceType;
        boolean red = result.result == result.diceNum;
        if (result.result == result.diceNum * result.diceType) {
            color = Color.GREEN;
        } else if (result.result == result.diceNum) {
            color = Color.RED;
        } else {
            color = Color.GRAY;
        }
        if (result.animated || (!green && !red)) {
            resultText.setTextColor(color);
        }
        resultText.setText("" + result.result);

        TextView calculation = (TextView) viewHolder.itemView.findViewById(R.id.roll_results);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (int i = 0; i < result.results.length; i++) {
            int detaileColor;
            if (result.results[i] == 1) {
                detaileColor = Color.RED;
            } else if (result.results[i] == result.diceType) {
                detaileColor = Color.GREEN;
            } else {
                detaileColor = Color.GRAY;
            }
            String num = "" + result.results[i];
            builder.append(num);
            builder.setSpan(new ForegroundColorSpan(detaileColor), builder.length() - num.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            if (i < result.results.length - 1) {
                builder.append(" + ");
            }
        }
        calculation.setText(builder);
        if (!result.animated) {
            List<Animator> animators = new ArrayList<>();
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    result.animated = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            if (green || red) {
                final ObjectAnimator animator = ObjectAnimator.ofInt(resultText, "textColor", Color.GRAY, color);
                animator.setDuration(1000);
                animator.setEvaluator(new ArgbEvaluator());
                animators.add(animator);
            }
            animatorSet.playTogether(animators);
            animatorSet.start();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.list_roll;
    }
}
