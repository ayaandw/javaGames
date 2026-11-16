package Yahtzee;

public class SixesScore extends Score {
    public SixesScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 0;
        for(int v : vals) {
            if (v == 6) count += 6;
        }
        return count;
    }
}
