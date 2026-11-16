package Yahtzee;
import java.util.HashMap;

public class ThreeOfAKindScore extends Score{

    public ThreeOfAKindScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        HashMap<Integer, Integer> c = new HashMap<>();
        int sum = 0;
        for (int v : vals) {
            sum += v;
            c.put(v, c.getOrDefault(v, 0) + 1);
        }
        for(int count : c.values()) {
            if (count >= 3) {
                return sum;
            }
        }
        return 0;
    }
}
