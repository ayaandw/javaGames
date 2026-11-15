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
    private JButton[] diceButtons = new JButton[5];
    private JButton rollButton;
    private JButton[] scoreButtons = new JButton[13];
    private JLabel totalScore;

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
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel dicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        for(int i = 0; i < 5; i++) {
            final int ii = i;
            diceButtons[i] = new JButton("Die " + (i+1) + "?");
            diceButtons[i].setFont(new Font("Arial", Font.BOLD, 14));
            diceButtons[i].setPreferredSize(new Dimension(110, 80));
            diceButtons[i].setBackground(Color.LIGHT_GRAY);
            diceButtons[i].setFocusPainted(false);

            diceButtons[i].addActionListener(e -> {
                if (hasRolled) {
                    holds[ii] = !holds[ii];
                    updateVisuals();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            });
            dicePanel.add(diceButtons[i]);
        }
        center.add(dicePanel);

        JPanel rollCPanel = new JPanel();
        rollButton = new JButton("🎲 Roll Dice");
        rollButton.setFont(new Font("Arial", Font.BOLD, 16));
        rollButton.addActionListener(e -> doRoll());
        rollCPanel.add(rollButton);
        center.add(rollCPanel);
        add(center, BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(14, 1, 3, 3));
        right.setBorder(BorderFactory.createTitledBorder("Scorecard (Click to Lock in)"));

        for(int i = 0; i < 13; i++) {
            final int catI = i;
            scoreButtons[i] = new JButton(scorecard[i].getName() + " : [Roll First]");
            scoreButtons[i].setEnabled(false);
            scoreButtons[i].addActionListener(e -> selectCategory(catI));
            right.add(scoreButtons[i]);
        }

        totalScore = new JLabel("Total Score: 0", SwingConstants.CENTER);
        totalScore.setFont(new Font("Arial", Font.BOLD, 14));
        right.add(totalScore);
        add(right, BorderLayout.EAST);

        resetRound();
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
            diceButtons[i].setBackground(Color.LIGHT_GRAY);
            diceButtons[i].setText("<html><center>Die " + (i+1) + "<br>[?]</center></html>");
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
            if(holds[i]) {
                diceButtons[i].setBackground(new Color(144, 238, 144));
                diceButtons[i].setText("<html><center><b>HELD</b><br>[" + vals[i] + "]</center></html>");
            } else {
                diceButtons[i].setBackground(Color.LIGHT_GRAY);
                diceButtons[i].setText("<html><center>Die " + (i + 1) + "<br>[" + vals[i] + "]</center></html>");
            }
        }
    }

    private void updatePotential() {
        int[] vals = gameDice.getDiceValues();
        for(int i = 0; i < 13; i++) {
            if(!scorecard[i].isUsed()) {
                int potential = scorecard[i].calculateScore(vals);
                scoreButtons[i].setText(scorecard[i].getName() + " : +" + potential);
                scoreButtons[i].setEnabled(true);
            }
        }
    }

    private void selectCategory(int i) {
        int[] vals = gameDice.getDiceValues();
        int iScore = scorecard[i].calculateScore(vals);

        scorecard[i].setVal(iScore);
        scorecard[i].setUsed(true);
        scoreButtons[i].setText(scorecard[i].getName() + " : " + iScore + " (Used)");
        scoreButtons[i].setEnabled(false);
        scoreButtons[i].setBackground(new Color(255, 228, 181));

        int total = 0;
        for (Score s : scorecard) {
            if (s.isUsed()) total += s.getVal();
        }
        totalScore.setText("Total Score: " + total);

        if (round < 13) {
            round++;
            resetRound();
        } else {
            statusLabel.setText("Game Over! Final Score: " + total);
            JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + total, "Victory!", JOptionPane.INFORMATION_MESSAGE);
            rollButton.setEnabled(false);
            for (JButton button : scoreButtons) button.setEnabled(false);
        }
    }
}
