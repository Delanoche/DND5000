package connorhenke.com.dnd5000;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

public class Spell {

    @SerializedName("name")
    private String name;

    @SerializedName("casting_measure")
    private String castingTimeMeasure;

    @SerializedName("casting_time")
    private int castingTime;

    @SerializedName("casting_qualifier")
    private String castingQualifier;

    @SerializedName("v")
    private boolean verbal;

    @SerializedName("s")
    private boolean somatic;

    @SerializedName("m")
    private boolean material;

    @SerializedName("material_qualifier")
    private String materialQualifier;

    @SerializedName("description")
    private String description;

    @SerializedName("concentration")
    private boolean concentration;

    @SerializedName("duration_measure")
    private String duration;

    @SerializedName("duration")
    private int durationTime;

    @SerializedName("duration_up_to")
    private boolean durationUpTo;

    @SerializedName("level")
    private int level;

    @SerializedName("range_type")
    private String range;

    @SerializedName("range_distance")
    private int rangeDistance;

    @SerializedName("school")
    private String school;

    public String getRangeString() {
        if (range.equals("distance")) {
            if (rangeDistance == 0) {
                return "Touch";
            }
            return rangeDistance + " feet";
        } else {
            return StringUtils.capitalize(range);
        }
    }

    public String getComponentsString() {
        StringBuilder builder = new StringBuilder();
        if (verbal) {
            builder.append("V ");
        }
        if (somatic) {
            builder.append("S ");
        }
        if (material) {
            builder.append("M ");
            if (materialQualifier.length() > 0) {
                builder.append("(");
                builder.append(materialQualifier);
                builder.append(")");
            }

        }
        return builder.toString();
    }

    public String getDurationString() {
        StringBuilder builder = new StringBuilder();
        if (concentration) {
            builder.append("concentration, ");
        }
        if (durationUpTo) {
            builder.append("up to ");
        }
        if (durationTime == 0) {
            builder.append("instantaneous");
        } else {
            String temp = duration;
            if (durationTime >= 1440) {
                durationTime /= 1440;
                temp = "days";
            }
            if (durationTime >= 60) {
                durationTime /= 60;
                temp = "hours";
            }
            builder.append(durationTime);
            builder.append(" ");
            builder.append(temp);
        }
        return StringUtils.capitalize(builder.toString());
    }

    public String getSchoolString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Level ");
        builder.append(level);
        builder.append(" ");
        builder.append(school);
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public String getCastingTimeMeasure() {
        return castingTimeMeasure;
    }

    public int getCastingTime() {
        return castingTime;
    }

    public String getCastingQualifier() {
        return castingQualifier;
    }

    public boolean isVerbal() {
        return verbal;
    }

    public boolean isSomatic() {
        return somatic;
    }

    public boolean isMaterial() {
        return material;
    }

    public String getMaterialQualifier() {
        return materialQualifier;
    }

    public String getDescription() {
        return description;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public String getDuration() {
        return duration;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public boolean isDurationUpTo() {
        return durationUpTo;
    }

    public int getLevel() {
        return level;
    }

    public String getRange() {
        return range;
    }

    public int getRangeDistance() {
        return rangeDistance;
    }

    public String getSchool() {
        return school;
    }
}
