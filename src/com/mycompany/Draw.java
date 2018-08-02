package com.mycompany;

import javax.swing.*;
import java.awt.*;

public class Draw extends JComponent implements Runnable {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for(int i = 10; i < 40; i = i +3) {
            //g.clearRect();
            g2.drawRect(i, i, 10, 10);
        }

        for (Node node: Main.nodes){
            g2.draw(node); // how to draw node? - X
        }

        //circle with number inside (has to have bounds - check for connection to happen when pressed and released)
        // circle change possition if in bounds click
        // straight line (if from bound to bound)



    }

    @Override
    public void run() {
        this.repaint();
    }
}
