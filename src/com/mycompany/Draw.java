package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Draw extends JComponent {

    ArrayList<Vertex> vertices = new ArrayList<>();
    Vertex vertex;
    Point point = new Point();
    Point start, end;
    boolean intersects = false;
    Rectangle vertexToCheck;
    int mouseButtonPressed, i;

    public Draw(){
        this.addMouseMotionListener(new MouseMotionAdapter() {
            //whats wrong: after clicking other vertex they merge together
            //
            @Override
            public void mouseDragged(MouseEvent e) {
                //super.mouseDragged(e);
                point.setLocation(e.getX() - 13, e.getY() - 13);
                //System.out.println("mouseDraggedButton: " + mouseButtonPressed + " intersects: " + intersects + " i: " + i);
                if(intersects & (mouseButtonPressed == MouseEvent.BUTTON3)){
                    vertices.get(i).setPoint(new Point(point));
                    //System.out.println(vertices.toString());
                    //??????????????????????????????????????????????????????????????????????????????????????????
                    //intersects = false;
                    System.out.print("D*");
                }

                System.out.print("D");
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                intersects = false;
                i = -1;
                start = new Point(e.getX()- 13, e.getY()- 13);
                vertexToCheck = new Rectangle(new Point(e.getX() - 13, e.getY() - 13), new Dimension(30, 30));
                mouseButtonPressed = e.getButton(); // because mouseDragged show 0 not 3 as intended
                for (Vertex vertex: vertices){
                    i++;
                    if(vertex.getBounds().intersects(vertexToCheck)) { // if any vertex intersects with rec then its true
                        intersects = true;
                        System.out.print("P*");
                        //System.out.println(vertex.point.x +  " " + vertex.point.y);
                        break;
                    }
                }
                System.out.println("P");
                //intersects = false;
            }
            @Override
            public void mouseReleased(MouseEvent e) {

                end = new Point(e.getX(), e.getY());
                //System.out.println("mouseReleaseButton: " + e.getButton());
                if((!intersects) & e.getButton() == MouseEvent.BUTTON1){
                    vertex = new Vertex(new Point(e.getX() - 13, e.getY() - 13)); // first created here
                    vertices.add(vertex);
                    System.out.print("R*");
                }
                System.out.println("R");
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        //mouse listener:
        //clicked - circle
        // pressed and released - connection
        //circle with number inside (has to have bounds - check for connection to happen when pressed and released)
        // circle change possition if in bounds click
        // straight line (if from bound to bound)

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        for (Vertex vertex: vertices){
            //g2.draw(vertex.circle);
            g2.draw(new Ellipse2D.Double(vertex.point.x, vertex.point.y, 30 ,30));
            if(vertex.number < 10)
                g2.drawString(Integer.toString(vertex.number), vertex.point.x + 12, vertex.point.y + 20);
            else
                g2.drawString(Integer.toString(vertex.number), vertex.point.x + 8, vertex.point.y + 20);
            //System.out.println(vertex.number);
        }
    }
}
