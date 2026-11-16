package Yahtzee;

public class FoursScore extends Score {
    public FoursScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 0;
        for(int v : vals) {
            if (v == 4) count += 4;
        }
        return count;
    }
}
