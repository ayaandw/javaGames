package Yahtzee;

public class TwosScore extends Score{
    
    public TwosScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 0;
        for(int i = 0; i < vals.length; i++) {
            if(vals[i] == 2) {
                count += 2;
            }
        }
        return count;
    }
}
