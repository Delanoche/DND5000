package connorhenke.com.dnd5000;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Monster implements Parcelable {

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
    private int charisma;

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

    public int getCharisma() {
        return charisma;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.size);
        dest.writeString(this.type);
        dest.writeString(this.subtype);
        dest.writeString(this.alignment);
        dest.writeInt(this.armorClass);
        dest.writeInt(this.hitPoints);
        dest.writeString(this.hitDice);
        dest.writeString(this.speed);
        dest.writeInt(this.strength);
        dest.writeInt(this.dexterity);
        dest.writeInt(this.constitution);
        dest.writeInt(this.intelligence);
        dest.writeInt(this.wisdom);
        dest.writeInt(this.charisma);
        dest.writeInt(this.constitutionSave);
        dest.writeInt(this.intelligenceSave);
        dest.writeInt(this.wisdomSave);
        dest.writeInt(this.history);
        dest.writeInt(this.perception);
        dest.writeString(this.damageVulnerabilities);
        dest.writeString(this.damageResistances);
        dest.writeString(this.damageImmunities);
        dest.writeString(this.conditionImmunities);
        dest.writeString(this.senses);
        dest.writeString(this.languages);
        dest.writeString(this.challengeRating);
        dest.writeList(this.specialAbilities);
        dest.writeList(this.actions);
        dest.writeList(this.legendaryActions);
    }

    protected Monster(Parcel in) {
        this.name = in.readString();
        this.size = in.readString();
        this.type = in.readString();
        this.subtype = in.readString();
        this.alignment = in.readString();
        this.armorClass = in.readInt();
        this.hitPoints = in.readInt();
        this.hitDice = in.readString();
        this.speed = in.readString();
        this.strength = in.readInt();
        this.dexterity = in.readInt();
        this.constitution = in.readInt();
        this.intelligence = in.readInt();
        this.wisdom = in.readInt();
        this.charisma = in.readInt();
        this.constitutionSave = in.readInt();
        this.intelligenceSave = in.readInt();
        this.wisdomSave = in.readInt();
        this.history = in.readInt();
        this.perception = in.readInt();
        this.damageVulnerabilities = in.readString();
        this.damageResistances = in.readString();
        this.damageImmunities = in.readString();
        this.conditionImmunities = in.readString();
        this.senses = in.readString();
        this.languages = in.readString();
        this.challengeRating = in.readString();
        this.specialAbilities = new ArrayList<Action>();
        in.readList(this.specialAbilities, Action.class.getClassLoader());
        this.actions = new ArrayList<Action>();
        in.readList(this.actions, Action.class.getClassLoader());
        this.legendaryActions = new ArrayList<Action>();
        in.readList(this.legendaryActions, Action.class.getClassLoader());
    }

    public static final Parcelable.Creator<Monster> CREATOR = new Parcelable.Creator<Monster>() {
        @Override
        public Monster createFromParcel(Parcel source) {
            return new Monster(source);
        }

        @Override
        public Monster[] newArray(int size) {
            return new Monster[size];
        }
    };

    public String getHeaderString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size);
        builder.append(" ");
        builder.append(type);

        if (subtype != null && subtype.length() > 0) {
            builder.append(" (");
            builder.append(subtype);
            builder.append(")");
        }

        builder.append(", ");
        builder.append(alignment);
        return builder.toString();
    }

    public String getHitPointString() {
        StringBuilder builder = new StringBuilder();
        builder.append(hitPoints);
        builder.append(" (");
        builder.append(hitDice);
        builder.append(")");

        return builder.toString();
    }



    public String getStatString(int value) {
        StringBuilder builder = new StringBuilder();
        builder.append(value);
        builder.append(" (");

        int modifier = (int) Math.floor((value - 10) / 2);
        builder.append(modifier >= 0 ? "+" : "");
        builder.append(modifier);
        builder.append(")");

        return builder.toString();
    }
}
