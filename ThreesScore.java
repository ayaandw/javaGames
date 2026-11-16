package Yahtzee;

public class ThreesScore extends Score{
    
    public ThreesScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 0;
        for (int v : vals) {
            if (v == 3) count += 3;
        }
        return count;
    }
}
