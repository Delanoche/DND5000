package connorhenke.com.dnd5000;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Action implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.attackBonus);
        dest.writeString(this.damageDice);
        dest.writeInt(this.damageBonus);
    }

    protected Action(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.attackBonus = in.readInt();
        this.damageDice = in.readString();
        this.damageBonus = in.readInt();
    }

    public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel source) {
            return new Action(source);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}
