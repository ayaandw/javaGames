package Yahtzee;

public class ChanceScore extends Score {
    public ChanceScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int sum = 0;
        for(int i = 0; i < vals.length; i++) {
            sum += vals[i];
        }
        return sum;
    }
}
