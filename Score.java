package Yahtzee;

public abstract class Score {
    private String name;
    private int val;
    private boolean isUsed;

    public abstract int calculateScore(int[] vals);

    public Score(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void used() {
        isUsed = true;
    }
}
