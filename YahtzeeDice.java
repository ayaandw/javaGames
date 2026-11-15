package Yahtzee;

public class YahtzeeDice {
	private Die[] dice = new Die[5];

	public YahtzeeDice() {
		for(int i = 0; i < dice.length; i++) {
			dice[i] = new Die();
		}
	}

	public YahtzeeDice(int numSides) {
		for (int i = 0; i < dice.length; i++) {
			dice[i] = new Die(numSides);
		}
	}

	public int roll() {
		int sumDice = 0;
		for(int i = 0; i < dice.length; i++) {
			
			sumDice += dice[i].roll();
		}
		return sumDice;
	}
	public int roll(boolean[] holds) {
		int sumDice = 0;
		for(int i = 0; i < dice.length; i++) {
			if(holds[i] == false) {
				sumDice += dice[i].roll();
			}
		}
		return sumDice;
	}

	public Die[] getDice() {
		return dice;
	}
	

	public int[] getDiceValues() {
		int[] vals = new int[5];
		for(int i = 0; i < dice.length; i++) {
			vals[i] = dice[i].getCurrentValue();
		}
		return vals;
	}
	
	public String toString() {
		String yahtzee = "The 5 dice read: ";
		for (int i = 0; i < dice.length; i++) {
			if (i < dice.length - 1)
				yahtzee += dice[i].getCurrentValue() + ", ";
			else
				yahtzee += dice[i].getCurrentValue() + ".";
		}
		return yahtzee;
	}
	
}
 