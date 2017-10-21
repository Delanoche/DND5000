package connorhenke.com.dnd5000;

import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    private GroupAdapter historyAdapter;
    private RecyclerView history;
    private RecyclerView nums;
    private NumsAdapter numsAdapter;
    private RecyclerView dice;
    private NumsAdapter diceAdapter;
    private TextView roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        setTitle("Dice");

        roll = (TextView) findViewById(R.id.roll);

        history = (RecyclerView) findViewById(R.id.rolls);
        history.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        historyAdapter = new GroupAdapter();
        /*
        historyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                if (item instanceof RollItem) {
                    RollItem rollItem = (RollItem) item;
                    int position;
                    if (!rollItem.result.expanded) {
                        position = historyAdapter.getAdapterPosition(rollItem) + 1;
                        historyAdapter.add(historyAdapter.getAdapterPosition(rollItem) + 1, new RollCalcItem(rollItem.result));
                    } else {
                        historyAdapter.remove(historyAdapter.getItem(historyAdapter.getAdapterPosition(rollItem) + 1));
                        position = historyAdapter.getAdapterPosition(rollItem);
                    }
                    if (position == historyAdapter.getItemCount() - 1) {
                        history.scrollToPosition(position);
                    }
                    rollItem.result.expanded = !rollItem.result.expanded;
                }
            }
        });
        */
        history.setAdapter(historyAdapter);

        nums = (RecyclerView) findViewById(R.id.nums);
        final List<String> numList = new ArrayList<>(10);
        numList.add("1");
        numList.add("2");
        numList.add("3");
        numList.add("4");
        numList.add("5");
        numList.add("6");
        numList.add("7");
        numList.add("8");
        numList.add("9");
        numList.add("0");
        numsAdapter = new NumsAdapter(this, numList, R.layout.list_num, new NumsAdapter.NumClickListener() {
            @Override
            public void clicked(String num) {
                roll.append(num);
            }
        });

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
//        nums.setLayoutManager(layoutManager);
        GridLayoutManager grid = new GridLayoutManager(this, 3);
        grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(numList.get(position)){
                    case "0":
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        nums.setLayoutManager(grid);
        nums.setAdapter(numsAdapter);
        numsAdapter.notifyDataSetChanged();

        dice = (RecyclerView) findViewById(R.id.dice);

        final List<String> diceList = new ArrayList<>(10);
        diceList.add("d4");
        diceList.add("d6");
        diceList.add("d8");
        diceList.add("d10");
        diceList.add("d12");
        diceList.add("d20");
        diceList.add("d100");
        diceAdapter = new NumsAdapter(this, diceList, R.layout.list_die, new NumsAdapter.NumClickListener() {
            @Override
            public void clicked(String num) {
                String mult = roll.getText().toString();
                int multiplier = mult.isEmpty() ? 1 : Integer.parseInt(mult);
                int die = Integer.parseInt(num.replaceAll("d", ""));
                roll.setText("");
                Random random = new Random();
                int total = 0;
                int[] array = new int[multiplier];
                for (int i = 0; i < multiplier; i++) {
                    int current = random.nextInt(die) + 1;
                    total += current;
                    array[i] = current;
                }
                historyAdapter.add(new RollItem(new RollResult(multiplier, die, total, array, 0)));
                history.scrollToPosition(historyAdapter.getItemCount() - 1);
            }
        });
        GridLayoutManager diceGrid = new GridLayoutManager(this, 3);
        diceGrid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (diceList.size() - 1 == position) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        dice.setLayoutManager(diceGrid);
        dice.setAdapter(diceAdapter);
        diceAdapter.notifyDataSetChanged();
    }
}
