package Yahtzee;
import javax.swing.*;
import java.awt.*;

public class YahtzeeGUI extends JFrame {
    private YahtzeeDice gameDice;
    private Score[] scorecard = {
        new OnesScore("Ones"), new TwosScore("Twos"), new ThreesScore("Threes"), new FoursScore("Fours"),
		new FivesScore("Fives"), new SixesScore("Sixes"), new ThreeOfAKindScore("3 of a Kind"),
		new FourOfAKindScore("4 of a Kind"), new SmallStraightScore("Small Straight"), new LargeStraightScore("Large Straight"),
		new FullHouseScore("Full House"), new YahtzeeScore("Yahtzee"), new ChanceScore("Chance")
    };
    private int round = 1;
    private int rolls = 3;
    private boolean[] holds = {false, false, false, false, false};
    private boolean hasRolled = false;

    private JLabel statusLabel;
    private JLabel roundLabel;
    private DiceButton[] diceButtons = new DiceButton[5];
    private JButton rollButton;
    private JButton[] scoreButtons = new JButton[13];
    private JLabel totalScore;
    private JLabel bonus;
    private JLabel upperBonus;

    public static void main(String args[]) {
    SwingUtilities.invokeLater(() -> {
        new YahtzeeGUI().setVisible(true);
    });
}

    public YahtzeeGUI() {
        setTitle("Yahtzee Arcade");
        setSize(900, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        gameDice = new YahtzeeDice();

        JPanel top = new JPanel(new GridLayout(2, 1));
        roundLabel = new JLabel("Round " + round + " of 13 | Rolls left: 3", SwingConstants.CENTER);
        roundLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel = new JLabel("Click 'Roll Dice' to start your turn!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        top.add(roundLabel);
        top.add(statusLabel);
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel diceContainer = new JPanel();
        diceContainer.setLayout(new BoxLayout(diceContainer, BoxLayout.Y_AXIS));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        for(int i = 0; i < 5; i++) {
            final int ii = i;
            diceButtons[i] = new DiceButton(i);
            diceButtons[i].setMargin(new Insets(0, 0, 0, 0));
            diceButtons[i].addActionListener(e -> {
                if (hasRolled) {
                    holds[ii] = !holds[ii];
                    updateVisuals();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            });
            
            if (i < 3) {
                row1.add(diceButtons[i]);
            }
            else {
                row2.add(diceButtons[i]);
            }
        }
        diceContainer.add(row1);
        diceContainer.add(row2);
        center.add(diceContainer);

        JPanel rollCPanel = new JPanel();
        rollButton = new JButton("🎲 Roll Dice");
        rollButton.setFont(new Font("Arial", Font.BOLD, 16));
        rollButton.addActionListener(e -> doRoll());
        rollCPanel.add(rollButton);
        center.add(rollCPanel);
        add(center, BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(BorderFactory.createTitledBorder("Scorecard (Click to Lock in)"));

        JPanel upperPanel = new JPanel(new GridLayout(7, 1, 3, 3));
        upperPanel.setBorder(BorderFactory.createTitledBorder("Upper Section"));
        for(int i = 0; i <= 5; i++) {
            final int catI = i;
            scoreButtons[i] = new JButton(scorecard[i].getName() + " : [Roll First]");
            scoreButtons[i].setEnabled(false);
            scoreButtons[i].addActionListener(e -> selectCategory(catI));
            upperPanel.add(scoreButtons[i]);
        }
        upperBonus = new JLabel("Upper Scores: 0 / 63 (Bonus)", SwingConstants.CENTER);
        upperBonus.setFont(new Font("Arial", Font.PLAIN, 12));
        upperBonus.setForeground(new Color(70, 70, 70));
        upperPanel.add(upperBonus);
        right.add(upperPanel);

        JPanel lowerPanel = new JPanel(new GridLayout(7, 1, 3, 3));
        lowerPanel.setBorder(BorderFactory.createTitledBorder("Lower Section"));
        for(int i = 6; i < 13; i++) {
            final int catI = i;
            scoreButtons[i] = new JButton(scorecard[i].getName() + " : [Roll First]");
            scoreButtons[i].setEnabled(false);
            scoreButtons[i].addActionListener(e -> selectCategory(catI));
            lowerPanel.add(scoreButtons[i]);
        }
        right.add(lowerPanel);

        totalScore = new JLabel("Total Score: 0", SwingConstants.CENTER);
        totalScore.setFont(new Font("Arial", Font.BOLD, 14));

        bonus = new JLabel("Yahtzee Bonus: +0", SwingConstants.CENTER);
        bonus.setFont(new Font("Arial", Font.PLAIN, 13));
        bonus.setForeground(new Color(46, 139, 87));

        JPanel stats = new JPanel(new GridLayout(2, 1, 3, 3));
        stats.add(bonus);
        stats.add(totalScore);
        right.add(stats);

        add(right, BorderLayout.EAST);
        resetRound();
    }

    private int calcUpperBonus() {
        int total = 0;
        for(int i = 0; i <= 5; i++) {
            if (scorecard[i].isUsed()) {
                total += scorecard[i].getVal();
            }
        }
        return total;
    }

    private int getScore(int i, int[] vals) {
        YahtzeeScore yScore = (YahtzeeScore) scorecard[11];

        if(yScore.isBonus(vals)) {
            int value = vals[0];
            if(scorecard[value - 1].isUsed()) {
                if (i == 8) return 30; //S straight
                if (i == 9) return 40; //L straight
                if (i == 10) return 25; //full house
            }
        }
        return scorecard[i].calculateScore(vals);
    }

    private void resetRound() {
        rolls = 2;
        hasRolled = false;

        rollButton.setEnabled(true);
        rollButton.setText("🎲 Roll Dice");
        roundLabel.setText("Round " + round + " of 13 | Rolls left: 3");
        statusLabel.setText("Click 'Roll Dice' to begin your turn!");
        for(int i = 0; i < 5; i++) {
            holds[i] = false;
            diceButtons[i].update(1, false);
        }

        for (int i = 0; i < 13; i++) {
            if (!scorecard[i].isUsed()) {
                scoreButtons[i].setEnabled(false);
                scoreButtons[i].setText(scorecard[i].getName() + " : Roll First");
            }
        }
    }

    private void doRoll() {
        if (!hasRolled) {
            hasRolled = true;
            gameDice.roll(holds);
        } else if (rolls > 0) {
            rolls--;
            gameDice.roll(holds);
        }
        updateVisuals();
        roundLabel.setText("Round " + round + " of 13 | Rolls left this turn: " + rolls);

        if (rolls == 0 && hasRolled) {
            rollButton.setEnabled(false);
            rollButton.setText("No Rolls Left");
            statusLabel.setText("No rolls left! You must select a score on the right.");
        } else {
            statusLabel.setText("Click dice to toggle hold, then roll again or pick a category!");
        }
        updatePotential();
    }

    private void updateVisuals() {
        int[] vals = gameDice.getDiceValues();
        for(int i = 0; i < 5; i++) {
            diceButtons[i].update(vals[i], holds[i]);
        }
    }

    private void updatePotential() {
        int[] vals = gameDice.getDiceValues();
        YahtzeeScore yScore = (YahtzeeScore)scorecard[11];

        if(yScore.isBonus(vals)) {
            statusLabel.setText("Bonus YAHTZEE!! Lock in any open category for +100 bonus points.");
        }

        for(int i = 0; i < 13; i++) {
            if(!scorecard[i].isUsed()) {
                int potential = getScore(i, vals);
                scoreButtons[i].setText(scorecard[i].getName() + " : +" + potential);
                scoreButtons[i].setEnabled(true);
            }
        }
    }

    private void selectCategory(int i) {
        int[] vals = gameDice.getDiceValues();
        YahtzeeScore yScore = (YahtzeeScore) scorecard[11];
        int iScore = getScore(i, vals);

        if (yScore.isBonus(vals)) {
            yScore.giveBonus();
            bonus.setText("Yahtzee Bonus: +" + yScore.getBonus());
            JOptionPane.showMessageDialog(
                this,
                "Bonus Yahtzee! +100 points added!",
                "Bonus Yahtzee!!",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        scorecard[i].setVal(iScore);
        scorecard[i].setUsed(true);
        scoreButtons[i].setText(scorecard[i].getName() + " : " + iScore + " (Used)");
        scoreButtons[i].setEnabled(false);
        scoreButtons[i].setBackground(new Color(255, 228, 181));

        int upperTotal = calcUpperBonus();
        int upBonus = 0;
        if(upperTotal >= 63) {
            upBonus = 35;
            upperBonus.setText("Upper Section: " + upperTotal + " / 63 (Bonus Unlocked! +35)");
            upperBonus.setForeground(new Color(0, 120, 0));
        } else {
            upperBonus.setText("Upper Section: " + upperTotal + " / 63 (" + (63 - upperTotal) + " needed)");
        }

        int total = upperTotal + upBonus + yScore.getBonus();
        for(int idx = 6; idx < 13; idx++) {
            if (scorecard[idx].isUsed()) {
                total += scorecard[idx].getVal();
            }
        }
        totalScore.setText("Total Score: " + total);

        if (round < 13) {
            round++;
            resetRound();
        } else {
            roundLabel.setText("Game Over");
            statusLabel.setText("Game Over! Final Score: " + total);
            JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + total, "Victory!", JOptionPane.INFORMATION_MESSAGE);
            rollButton.setEnabled(false);
            for (JButton button : scoreButtons) button.setEnabled(false);
        }
    }
}
