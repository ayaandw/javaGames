package Yahtzee;

public class OnesScore extends Score {
    
    public OnesScore(String name) {
		super(name);
	}

    public int calculateScore(int[] vals) {
        int count = 0;
        for(int i = 0; i < vals.length; i++) {
            if(vals[i] == 1) {
                count++;
            }
        }
        used();
        return count;
    }
}