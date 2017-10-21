package connorhenke.com.dnd5000;

public class RollResult {

    public int diceNum;
    public int diceType;
    public int addition;
    public int result;
    public int[] results;
    boolean expanded;
    boolean animated;

    public RollResult(int diceNum, int diceType, int result, int[] results, int addition) {
        this.diceNum = diceNum;
        this.diceType = diceType;
        this.result = result;
        this.results = results;
        this.addition = addition;
        this.expanded = false;
        this.animated = false;
    }
}
