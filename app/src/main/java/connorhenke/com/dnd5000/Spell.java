package connorhenke.com.dnd5000;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Spell {

    private String name;
    private String castingTimeMeasure;
    private int castingTime;
    private String castingQualifier;
    private boolean visual;
    private boolean somatic;
    private boolean material;
    private String materialQualifier;
    private String description;
    private boolean concentration;
    private String duration;
    private int durationTime;
    private boolean durationUpTo;
    private int level;
    private String range;
    private int rangeDistance;
    private String school;

    public Spell(String name, String castingTimeMeasure, int castingTime, String castingQualifier, boolean visual, boolean somatic, boolean material, String materialQualifier, String description, boolean concentration, String duration, int durationTime, boolean durationUpTo, int level, String range, int rangeDistance, String school) {
        this.name = name;
        this.castingTimeMeasure = castingTimeMeasure;
        this.castingTime = castingTime;
        this.castingQualifier = castingQualifier;
        this.visual = visual;
        this.somatic = somatic;
        this.material = material;
        this.materialQualifier = materialQualifier;
        this.description = description;
        this.concentration = concentration;
        this.duration = duration;
        this.durationTime = durationTime;
        this.durationUpTo = durationUpTo;
        this.level = level;
        this.range = range;
        this.rangeDistance = rangeDistance;
        this.school = school;
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

    public boolean isVisual() {
        return visual;
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

    public static Spell fromJson(JSONObject object) throws JSONException {
        String name = object.getString("name");
        int level = object.getInt("level");
        int rangeDistance = object.getInt("range_distance");
        String description = object.getString("description");
        int castingTime = object.getInt("casting_time");
        String castingMeasure = object.getString("casting_measure");
        String durationMeasure = object.getString("duration_measure");
        String rangeType = object.getString("range_type");
        boolean durationUpTo = object.getBoolean("duration_up_to");
        JSONObject components = object.getJSONObject("components");
        boolean verbal = components.optBoolean("v", false);
        boolean somatic = components.optBoolean("s", false);
        boolean material = components.optBoolean("m", false);
        int durationTime = object.getInt("duration");
        boolean concentration = object.getBoolean("concentration");
        String materialQualifier = object.getString("material_qualifier");
        String castingQualifier = object.getString("casting_qualifier");
        String school = object.getString("school");

        Spell spell = new Spell(name,
                castingMeasure,
                castingTime,
                castingQualifier,
                verbal,
                somatic,
                material,
                materialQualifier,
                description,
                concentration,
                durationMeasure,
                durationTime,
                durationUpTo,
                level,
                rangeType,
                rangeDistance,
                school);
        return spell;
    }
}
