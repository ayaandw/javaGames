package Yahtzee;
import javax.swing.*;
import java.awt.*;

public class DiceButton extends JButton {
    private int val = 1;
    private boolean held = false;
    private int dieNum;

    public DiceButton(int i) {
        dieNum = i;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(95, 95));
        setMinimumSize(new Dimension(95, 95));
        setMaximumSize(new Dimension(95, 95));
    }

    public void update(int val, boolean held) {
        this.val = val;
        this.held = held;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        if (held) {
            g2d.setColor(new Color(210, 245, 210));
        } else {
            g2d.setColor(Color.WHITE);
        }
        g2d.fillRoundRect(2, 2, w - 4, h - 4, 18, 18);

        if (held) {
            g2d.setColor(new Color(46, 139, 87));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRoundRect(2, 2, w - 4, h - 4, 18, 18);
        }

        g2d.setColor(held ? new Color(0, 100, 0) : Color.GRAY);
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("Die " + (dieNum + 1) + (held ? " (HELD)" : ""), 10, 18);

        if (val < 1 || val > 6) return;

        g2d.setColor(Color.BLACK);
        int size = w / 6;
        int x1 = w / 4 - size / 2;
        int x2 = w / 2 - size / 2;
        int x3 = (3 * w) / 4 - size / 2;

        int y1 = h / 4 - size / 2 + 5;
        int y2 = h / 2 - size / 2 + 5;
        int y3 = (3 * h) / 4 - size / 2 + 5;

        switch (val) { 
            case 1:
                Dot(g2d, x2, y2, size);
                break;
            case 2:
                Dot(g2d, x1, y1, size);
                Dot(g2d, x3, y3, size);
                break;
            case 3:
                Dot(g2d, x1, y1, size);
                Dot(g2d, x2, y2, size);
                Dot(g2d, x3, y3, size);
                break;
            case 4:
                Dot(g2d, x1, y1, size);
                Dot(g2d, x3, y1, size);
                Dot(g2d, x1, y3, size);
                Dot(g2d, x3, y3, size);
                break;
            case 5:
                Dot(g2d, x1, y1, size);
                Dot(g2d, x3, y1, size);
                Dot(g2d, x2, y2, size);
                Dot(g2d, x1, y3, size);
                Dot(g2d, x3, y3, size);
                break;
            case 6:
                Dot(g2d, x1, y1, size);
                Dot(g2d, x3, y1, size);
                Dot(g2d, x1, y2, size);
                Dot(g2d, x3, y2, size);
                Dot(g2d, x1, y3, size);
                Dot(g2d, x3, y3, size);
                break;
        }
    }
    
    private void Dot(Graphics2D g2d, int x, int y, int size) {
        g2d.fillOval(x, y, size, size);
    }
}
