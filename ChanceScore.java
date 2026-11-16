package Yahtzee;

public class ChanceScore extends Score {
    public ChanceScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int sum = 0;
        for (int v : vals) {
            sum += v;
        }
        return sum;
    }
}
