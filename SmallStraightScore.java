package Yahtzee;

public class SmallStraightScore extends Score {
    public SmallStraightScore(String name) {
        super(name);
    }

    public int calculateScore(int[] vals) {
        boolean[] present = new boolean[7];
        for (int v : vals) {
            if (v >= 1 && v <= 6) {
                present[v] = true;
            }
        }
        if((present[1] && present[2] && present[3] && present[4]) ||
           (present[2] && present[3] && present[4] && present[5]) ||
           (present[3] && present[4] && present[5] && present[6])) {
            return 30;
           }
        return 0;
    }
}
