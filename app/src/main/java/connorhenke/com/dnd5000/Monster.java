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

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getAlignment() {
        return alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String getHitDice() {
        return hitDice;
    }

    public String getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getNacharismame() {
        return nacharismame;
    }

    public int getConstitutionSave() {
        return constitutionSave;
    }

    public int getIntelligenceSave() {
        return intelligenceSave;
    }

    public int getWisdomSave() {
        return wisdomSave;
    }

    public int getHistory() {
        return history;
    }

    public int getPerception() {
        return perception;
    }

    public String getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public String getDamageResistances() {
        return damageResistances;
    }

    public String getDamageImmunities() {
        return damageImmunities;
    }

    public String getConditionImmunities() {
        return conditionImmunities;
    }

    public String getSenses() {
        return senses;
    }

    public String getLanguages() {
        return languages;
    }

    public String getChallengeRating() {
        return challengeRating;
    }

    public List<Action> getSpecialAbilities() {
        return specialAbilities;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Action> getLegendaryActions() {
        return legendaryActions;
    }
}
