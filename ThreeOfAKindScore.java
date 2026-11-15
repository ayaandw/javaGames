package Yahtzee;
import java.util.Arrays;

public class ThreeOfAKindScore extends Score{

    public ThreeOfAKindScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        Arrays.sort(vals);
        int sum = 0;
        int count = 1;
        for(int i = 0; i < vals.length; i++) {
            sum += vals[i];
            if(i == 3) {
                continue;
            }
            if(vals[i] == vals[2]) {
                count++;
            }
        }
        if(count >= 3) {
            return sum;
        }
        return 0;
    }
}
