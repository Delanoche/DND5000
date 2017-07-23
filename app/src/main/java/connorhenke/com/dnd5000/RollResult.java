package connorhenke.com.dnd5000;

public class RollResult {

    public int diceNum;
    public int diceType;
    public int result;
    public int[] results;
    boolean expanded;
    boolean animated;

    public RollResult(int diceNum, int diceType, int result, int[] results) {
        this.diceNum = diceNum;
        this.diceType = diceType;
        this.result = result;
        this.results = results;
        this.expanded = false;
        this.animated = false;
    }
}
