package Yahtzee;
import java.util.Arrays;

public class LargeStraightScore extends Score{
    public LargeStraightScore(String name) {
        super(name);
    }
    
    public int calculateScore(int[] vals) {
        Arrays.sort(vals);
        for(int i = 0; i < vals.length - 1; i++) {
            if(vals[i] != vals[i+1] - 1) {
                return 0;
            }
        }
        return 40;
    }
}
