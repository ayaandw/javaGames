package Yahtzee;

public class TwosScore extends Score{
    
    public TwosScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 0;
        for (int v : vals) {
            if (v == 2) count += 2;
        }
        return count;
    }
}
