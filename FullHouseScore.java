package Yahtzee;
import java.util.Arrays;

public class FullHouseScore extends Score {
    public FullHouseScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        Arrays.sort(vals);
        if(vals[0] == vals[1]) {
            if(vals[0] == vals[2] && vals[3] == vals[4] && vals[4] != vals[0]) {
                return 25;
            }
            if(vals[2] == vals[3] && vals[2] == vals[4] && vals[4] != vals[0]) {
                return 25;
            }
        }
        return 0;
    }
}
