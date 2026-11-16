package Yahtzee;

public class FivesScore extends Score {
    public FivesScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 0;
        for (int v : vals) {
            if (v == 5) count += 5;
        }
        return count;
    }
}
