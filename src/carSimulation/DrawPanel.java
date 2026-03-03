package carSimulation;

import cars.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    ArrayList<DrawablePositionable> panels = new ArrayList<>();

    // Initializes the panel and reads the images
    public DrawPanel(int width, int height) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.green);
    }

    void addDrawable(DrawablePositionable drawable) {
        panels.add(drawable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(DrawablePositionable panel : panels) {
            int x = (int)panel.getPosition().getX();
            int y = (int)panel.getPosition().getY();
            Image img = panel.getImage();
            g.drawImage(panel.getImage(), (int)panel.getPosition().getX(), (int)panel.getPosition().getY(), null);
        }
    }
}
