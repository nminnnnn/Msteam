package view.ChatUI.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class CirclePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();
        int width = size.width;
        int height = size.height;
        int diameter = Math.min(width, height);
        g.setColor(Color.BLACK);
        g.fillOval(0, 0, diameter, diameter);
    }
}
