package Yahtzee;

public class YahtzeeScore extends Score{
	public YahtzeeScore(String name) {
		super(name);
	}
	
	public int calculateScore(int[] vals) {	
		for(int i = 0; i < vals.length; i++) {
			if(vals[i] != vals[0]) {
				return 0;
			}
		}
		return 50;
	} 
}
