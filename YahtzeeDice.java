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

	// return the array of Die objects
	public Die[] getDice() {
		return dice;
	}
	// If the format above isn't helpful
	// this method will return the int values of each Die object
	public int[] getDiceValues() {
		int[] vals = new int[5];
		for(int i = 0; i < dice.length; i++) {
			vals[i] = dice[i].getCurrentValue();
		}
		return vals;
	}
	// String representation of Pair of Dice
	// Again, don't reinvent the wheel -- call the toString() that
	// already exists for each Die object!
	public String toString() {
//		String returnString = "";
//		for(int i = 0; i < dice.length; i++) {
//			returnString += dice[i].toString() + ", ";
//		}
//		return returnString;
		
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
 