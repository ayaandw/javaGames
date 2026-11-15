package Yahtzee;
import java.util.Scanner;

public class YahtzeeGame {
	Scanner sc = new Scanner(System.in);
	private YahtzeeDice gameDice = new YahtzeeDice();
	private Score[] scorecard = {
		new OnesScore("Ones"), new TwosScore("Twos"), new ThreesScore("Threes"), new FoursScore("Fours"),
		new FivesScore("Fives"), new SixesScore("Sixes"), new ThreeOfAKindScore("3 of a Kind"),
		new FourOfAKindScore("4 of a Kind"), new SmallStraightScore("Small Straight"), new LargeStraightScore("Large Straight"),
		new FullHouseScore("Full House Score"), new YahtzeeScore("Yahtzee Score"), new ChanceScore("Chance Score")
	};

	//methods
	public void startGame() {
		System.out.println("Welcome to Yahtzee!");

		for(int round = 0; round < 13; round++) {
			System.out.println("Round " + (round + 1) + " of 13");
			playRound();
		}

		sc.close();
		System.out.println("Game Over! Final Scores: ");
		printScorecard();

		int finalScore = 0;
		for(int i = 0; i < 13; i++) {
			finalScore += scorecard[i].getVal();
		}
		System.out.println("Final Total Score: " + finalScore);
	}
	
	public void playRound() {
		boolean[] holds = {false, false, false, false, false};

		System.out.println("Rolling starting hand...");
		gameDice.roll(holds);
		System.out.println(gameDice);

		for(int i = 2; i > 0; i--) {

			System.out.print("Would you like to roll again? (y/n) ");
			String input = sc.nextLine();
			if (input.toLowerCase().equals("n")) {
				break;
			}
			for(int j = 0; j < 5; j++) {
				System.out.print("Hold die #" + (j + 1) + " (" + gameDice.getDiceValues()[j] + ")? (y/n): ");
				String doHold = sc.nextLine();

				if (doHold.toLowerCase().equals("n")) {
					holds[j] = false;
				}
				else if (doHold.toLowerCase().equals("y")) {
					holds[j] = true;
				}
			}

			System.out.println("\nRolling...");
			gameDice.roll(holds);
			System.out.println(gameDice);
			System.out.println((i-1) + " rolls left in this round.");
		}

		printScorecard();
		int selectedScore = -1;
		while(true) {
			System.out.println(gameDice);
			System.out.print("Choose an available category (1-13): ");
			selectedScore = Integer.valueOf(sc.nextLine()) - 1;
			if(scorecard[selectedScore].isUsed()) {
				System.out.println("That category is already used. Pick another!");
				continue;
			}
			break;
		}

		int[] finalVals = gameDice.getDiceValues();
		int roundScore = scorecard[selectedScore].calculateScore(finalVals);
		scorecard[selectedScore].setVal(roundScore);
		scorecard[selectedScore].setUsed(true);

		System.out.println("You scored " + roundScore + " points in " + scorecard[selectedScore].getName());
	}	

	public void printScorecard() {
		System.out.println("Current Scorecard:");
		for(int i = 0; i < scorecard.length; i++) {
			if(scorecard[i].isUsed()) {
				System.out.printf("%2d. %-20s %s\n", (i + 1), scorecard[i].getName(), "Already Used! (" + scorecard[i].getVal() + ")");
			}
			else {
				System.out.printf("%2d. %-20s %s\n", (i + 1), scorecard[i].getName(), "Available!");
			}
		}
	}
}
