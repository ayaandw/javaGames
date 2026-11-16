package Yahtzee;

public class YahtzeeScore extends Score{
	private int bonusCount = 0;

	public YahtzeeScore(String name) {
		super(name);
	}

	public int calculateScore(int[] vals) {	
		boolean isYahtzee = true;
		for(int i = 0; i < vals.length; i++) {
			if(vals[i] != vals[0]) {
				return 0;
			}
		}
		return 50;
	}

	public int getBonus() {
		return bonusCount * 100;
	}

	public void giveBonus() {
		bonusCount++;
	}

	public boolean isBonus(int[] vals) {
		if (!isUsed() || getVal() != 50) return false;
		for(int v : vals) {
			if (v != vals[0]) return false;
		}
		return true;
	}
}
