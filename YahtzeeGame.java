package Yahtzee;
import java.util.Scanner;

public class YahtzeeGame {
	private YahtzeeDice gameDice = new YahtzeeDice();
	// private ScoreCard score;
	
	//methods
	public void startGame() {
		System.out.println("Welcome to Yahtzee!");
//		for(int i = 0; i < 13; i++) {
//			playRound();
//		}
		playRound();
	}
	
	public void playRound() {
		Scanner sc = new Scanner(System.in);
		boolean[] holds = {false, false, false, false, false};
		for(int i = 2; i > 0; i--) {
			System.out.println();
			System.out.println("Rolling...");
			gameDice.roll(holds);
			System.out.println(gameDice);
			System.out.println(i + " turns left in this round");
			System.out.print("Would you like to roll again? (y/n) ");
			String input = sc.nextLine();
			if (input.equals("n")) {
				break;
			}
			for(int j = 0; j < 5; j++) {
				System.out.print("Hold die #" + (j + 1) + "? ");
				String doHold = sc.nextLine();
				if (doHold.equals("n")) {
					holds[j] = false;
				}
				else if (doHold.equals("y")) {
					holds[j] = true;
				}
			}
		}
		gameDice.roll(holds);
		System.out.println(gameDice);
		System.out.println("0 turns left in this round");
		sc.close();
	}
}
