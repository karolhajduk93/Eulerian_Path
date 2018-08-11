package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Draw extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        for (Vertex vertex: Main.vertices){
            //g2.draw(vertex.circle);
            g2.draw(new Ellipse2D.Double(vertex.point.x, vertex.point.y, 30 ,30));
            if(vertex.number < 10)
                g2.drawString(Integer.toString(vertex.number), vertex.point.x + 12, vertex.point.y + 20);
            else
                g2.drawString(Integer.toString(vertex.number), vertex.point.x + 8, vertex.point.y + 20);
            //System.out.println(vertex.number);
        }

        //circle with number inside (has to have bounds - check for connection to happen when pressed and released)
        // circle change possition if in bounds click
        // straight line (if from bound to bound)



    }
}
