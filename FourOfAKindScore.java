package Yahtzee;
import java.util.Arrays;

public class FourOfAKindScore extends Score {
    public FourOfAKindScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        int count = 1;
        int sum = 0;
        Arrays.sort(vals);
        for(int i = 0; i < vals.length; i++) {
            sum += vals[i];
            if(i == 3) {
                continue;
            }
            if(vals[i] == vals[2]) {
                count++;
            }
        }
        if(count >= 4) {
            return sum;
        }
        return 0;
    }
}
