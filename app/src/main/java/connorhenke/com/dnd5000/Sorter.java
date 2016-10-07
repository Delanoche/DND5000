package connorhenke.com.dnd5000;

import java.util.Comparator;

public class Sorter {

    public static Comparator<Spell> alphabetically() {
        return new Comparator<Spell>() {
            @Override
            public int compare(Spell spell, Spell t1) {
                return spell.getName().compareTo(t1.getName());
            }
        };
    }

    public static Comparator<Spell> byRange() {
        return new Comparator<Spell>() {
            @Override
            public int compare(Spell spell, Spell t1) {
                String range = spell.getRange();
                String otherRange = t1.getRange();
                if (spell.getRange().equals("self")) {
                    return -1;
                } else if (range.equals("touch") && otherRange.equals("self")) {
                    return 1;
                } else if (range.equals("touch")) {
                    return  -1;
                } else {
                    String[] feet1 = range.split(" ");
                    String[] feet2 = otherRange.split(" ");
                    int num1 = Integer.parseInt(feet1[0]);
                    int num2 = Integer.parseInt(feet2[0]);
                    return Integer.valueOf(num1).compareTo(num2);
                }
            }
        };
    }

    public static Comparator<Spell> byLevel() {
        return new Comparator<Spell>() {
            @Override
            public int compare(Spell spell, Spell t1) {
                return Integer.valueOf(spell.getLevel()).compareTo(t1.getLevel());
            }
        };
    }

    public static Comparator<Spell> byDuration() {
        return new Comparator<Spell>() {
            @Override
            public int compare(Spell spell, Spell t1) {
                return Integer.valueOf(spell.getDurationTime()).compareTo(t1.getDurationTime());
            }
        };
    }
}
