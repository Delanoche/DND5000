package connorhenke.com.dnd5000;

import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String description;

    @SerializedName("attack_bonus")
    private int attackBonus;

    @SerializedName("damage_dice")
    private String damageDice;

    @SerializedName("damage_bonus")
    private int damageBonus;
}
