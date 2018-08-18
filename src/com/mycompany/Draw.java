package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Draw extends JComponent {

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line2D> lines = new ArrayList<>();
    HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    Vertex vertexGlobal;
    Point point = new Point(), start, end;
    Line2D line;
    boolean intersects = false, contains = false;
    Rectangle vertexToCheck;
    int mouseButtonPressed, i, number;

    public Draw(){

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                point.setLocation(e.getX() - 13, e.getY() - 13);
                vertexToCheck = new Rectangle(point, new Dimension(30, 30));

                //vertices intersects handle
                for (Vertex vertex: vertices){
                    if(vertex == vertices.get(i))
                        continue;
                    if(vertex.getBounds().intersects(vertexToCheck) & mouseButtonPressed == MouseEvent.BUTTON3){
                        intersects = false;
                    }
                }
                //new coordinates for vertex
                if(intersects & (mouseButtonPressed == MouseEvent.BUTTON3)){
                    vertices.get(i).setPoint(new Point(point));
                    //moving lines with vertices. hmmmmmmmmmmmmmm????? ***************************************
                }

                //CREATING LINES BETWEEN VERTICES (Dragged)
                if(contains & (mouseButtonPressed == MouseEvent.BUTTON1)){
                    end = e.getPoint();
                }
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                //CREATING AND MOVING VERTICES
                intersects = false;
                i = -1;

                point.setLocation(e.getX() - 13, e.getY() - 13);
                vertexToCheck = new Rectangle(point, new Dimension(30, 30));

                mouseButtonPressed = e.getButton(); // because mouseDragged show 0 not 3 or 1 as intended
                for (Vertex vertex: vertices) { // checking if any vertex intersects in moment of clicking
                    i++;
                    if (vertex.getBounds().intersects(vertexToCheck)) {
                        intersects = true;
                        break;
                    }

                }
                //CREATING LINES BETWEEN VERTICES (Pressed)
                for (Vertex vertex: vertices){
                    if(vertex.getBounds().contains(e.getPoint()) & mouseButtonPressed == MouseEvent.BUTTON1){
                        number = vertex.number;
                        start = new Point(vertex.point.x + 15, vertex.point.y + 15);
                        end = start;
                        contains = true;
                        break;
                    }
                }

            }
            @Override
            public void mouseReleased(MouseEvent e) {

                //CREATING AND MOVING VERTICES
                i = -1;
                point.setLocation(e.getX() - 13, e.getY() - 13);
                vertexToCheck = new Rectangle(point, new Dimension(30, 30));
                mouseButtonPressed = e.getButton(); // because mouseDragged show 0 not 3 or 1 as intended
                for (Vertex vertex: vertices){ // checking if any vertex intersects in moment of clicking
                    if(vertex.getBounds().intersects(vertexToCheck)) {
                        intersects = true;
                        break;
                    }
                }
                if((!intersects) & e.getButton() == MouseEvent.BUTTON1 /*& end == null*/){
                    vertexGlobal = new Vertex(new Point(e.getX() - 13, e.getY() - 13)); //creating new vertex
                    vertices.add(vertexGlobal);
                }

                //CREATING LINES BETWEEN VERTICES (Release)
                if(contains & (mouseButtonPressed == MouseEvent.BUTTON1)){
                    for (Vertex vertex: vertices){
                        if(vertex.getBounds().contains(e.getPoint()) & mouseButtonPressed == MouseEvent.BUTTON1
                                & vertex.number != number  & end != null & start != null){
                            //end = new Point(vertex.point.x + 15, vertex.point.y + 15);
                            end.setLocation(vertex.point.x + 15, vertex.point.y + 15);
                            line.setLine(start, end);
                            lines.add(line);
                        }
                    }

                }
                start = null;
                end = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        if(start != null && end!= null) {
            line = new Line2D.Float(start.x, start.y, end.x, end.y);
            g2.draw(line);
            System.out.println(line.getP2());
        }
        for(Line2D line: lines){
            g2.draw(line);
        }

        for (Vertex vertex: vertices){
            g2.draw(new Ellipse2D.Double(vertex.point.x, vertex.point.y, 30 ,30));
            if(vertex.number < 10)
                g2.drawString(Integer.toString(vertex.number), vertex.point.x + 12, vertex.point.y + 20);
            else
                g2.drawString(Integer.toString(vertex.number), vertex.point.x + 8, vertex.point.y + 20);
        }
    }
}
