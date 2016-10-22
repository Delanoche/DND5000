package connorhenke.com.dnd5000;

import java.util.Comparator;

public class MonsterSorter {

    public static Comparator<Monster> alphabetically() {
        return new Comparator<Monster>() {
            @Override
            public int compare(Monster monster, Monster t1) {
                return monster.getName().compareTo(t1.getName());
            }
        };
    }
}
