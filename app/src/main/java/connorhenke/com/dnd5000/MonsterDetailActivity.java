package connorhenke.com.dnd5000;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import connorhenke.com.dnd5000.databinding.ActivityMonsterDetailBinding;

public class MonsterDetailActivity extends AppCompatActivity {

    private Monster monster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMonsterDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_monster_detail);

        monster = (Monster) getIntent().getExtras().get("monster");
        binding.setMonster(monster);
    }
}
