package connorhenke.com.dnd5000;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Monster {

    @SerializedName("name")
    private String name;

    @SerializedName("size")
    private String size;

    @SerializedName("type")
    private String type;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("alignment")
    private String alignment;

    @SerializedName("armor_class")
    private int armorClass;

    @SerializedName("hit_points")
    private int hitPoints;

    @SerializedName("hit_dice")
    private String hitDice;

    @SerializedName("speed")
    private String speed;

    @SerializedName("strength")
    private int strength;

    @SerializedName("dexterity")
    private int dexterity;

    @SerializedName("constitution")
    private int constitution;

    @SerializedName("intelligence")
    private int intelligence;

    @SerializedName("wisdom")
    private int wisdom;

    @SerializedName("charisma")
    private int nacharismame;

    @SerializedName("constitution_save")
    private int constitutionSave;

    @SerializedName("intelligence_save")
    private int intelligenceSave;

    @SerializedName("wisdom_save")
    private int wisdomSave;

    @SerializedName("history")
    private int history;

    @SerializedName("perception")
    private int perception;

    @SerializedName("damage_vulnerabilities")
    private String damageVulnerabilities;

    @SerializedName("damage_resistances")
    private String damageResistances;

    @SerializedName("damage_immunities")
    private String damageImmunities;

    @SerializedName("condition_immunities")
    private String conditionImmunities;

    @SerializedName("senses")
    private String senses;

    @SerializedName("languages")
    private String languages;

    @SerializedName("challenge_rating")
    private String challengeRating;

    @SerializedName("special_abilities")
    private List<Action> specialAbilities;

    @SerializedName("actions")
    private List<Action> actions;

    @SerializedName("legendary_actions")
    private List<Action> legendaryActions;
}
