package Yahtzee;

public class Die {
	// instance variables
	private int numSides;
	private int currentValue;
	
	// constructors
	public Die() {
		numSides = 6;
		roll();
	}
	
	public Die(int numSides) {
		this.numSides = numSides;
		roll();
	}
	
	// instance methods
	public int roll() {
		currentValue = (int)(Math.random() * numSides) + 1;
		return currentValue;
	}
	
	public int getCurrentValue() {
		return currentValue;
	}
	
	public int getNumSides() {
		return numSides;
	}

	@Override
	public String toString() {
		return "Die [numSides=" + numSides + ", currentValue=" + currentValue + ", roll()=" + roll()
				+ ", getCurrentValue()=" + getCurrentValue() + ", getNumSides()=" + getNumSides() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
