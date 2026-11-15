package Yahtzee;
import java.util.HashMap;

public class FourOfAKindScore extends Score {
    public FourOfAKindScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        int sum = 0;
        for (int v : vals) {
            sum += v;
            counts.put(v, counts.getOrDefault(v, 0) + 1);
        }
        for(int count : counts.values()) {
            if(count >= 4) {
                return sum;
            }
        }
        return 0;
    }
}
