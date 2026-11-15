package Yahtzee;
import java.util.Arrays;

public class SmallStraightScore extends Score {
    public SmallStraightScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        Arrays.sort(vals);
        //2,2,3,4,6
        for(int i = 0; i < 3; i++) {
            if(vals[i] + 1 == vals[i+1] && vals[i+1] == vals[i+2] - 1 && vals[i+2] == vals[i+3] - 1) {
                return 30;
            }
        }
        return 0;
    }
}
