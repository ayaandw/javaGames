# javaGames

Currently only includes yahtzee and not other games because I focused on building that for week 4 of Hackclub Terra

---

A Windows app version of the dice game Yahtzee originally built as a practice of inheritance for APCS Unit 5, but went beyond assignment instructions to add UI components using Java Swing and JFrame to practice GUI coding

## How to Play
Download the build on https://ayaand.itch.io/yahtzee-game
 * From there, download the .zip file, extract, and run `Yahtzee.exe`
 * No JDK or Java files necessary at all, but only runs on Windows

 ---

## How to Play Yahtzee
The objective of Yahtzee is to score points by rolling five dice to achieve certain scoring combinations. The game runs for 13 rounds, with each round having its own score category selected at the end

* Dice: Roll up to 3 times per round, holding dice between rolls to make the best combinations possible
* Scorecard:
    * Upper Section: Ones through Sixes, score based on how many face values of a certain dice you have
    * Lower Section: 3 of a Kind, 4 of a Kind, Full House, Small Straight, Large Straight, Yahtzee, and Chance - each has its own requirements
* Bonuses: Score 63+ points in the upper section to earn +35 points, and get multiple Yahtzees to earn extra points!

For a complete version of the rules, read: https://grail.sourceforge.net/demo/yahtzee/rules.html (not my site!)

**Coolest Technical Features (in my opinion):**
* Custom drawn rounded squares and dots for the dice
* Clickable toggle hold system for each die
* Implemented bonuses for Yahtzees and upper scorecard
