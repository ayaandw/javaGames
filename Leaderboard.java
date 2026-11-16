package Yahtzee;
import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String FILE = "yahtzee_leaderboard.txt";
    private static final int MAX_SCORES = 5;

    public static class Entry implements Comparable<Entry> {
        String name;
        int score;

        public Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public int compareTo(Entry other) {
            return Integer.compare(other.score, this.score);
        }
    }

    public static List<Entry> loadScores() {
        List<Entry> scores = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return scores;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    scores.add(new Entry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        Collections.sort(scores);
        return scores;
    }

    public static void addScore(String name, int score) {
        List<Entry> scores = loadScores();
        scores.add(new Entry(name, score));
        Collections.sort(scores);

        if (scores.size() > MAX_SCORES) {
            scores = scores.subList(0, MAX_SCORES);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {
            for(Entry e : scores) {
                writer.println(e.name + ":" + e.score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
