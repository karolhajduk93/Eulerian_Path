package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame{

    public static ArrayList<Vertex> vertices = new ArrayList<>();
    Vertex vertex;
    Point point = new Point();
    Point start, end;
    boolean intersects = false;
    Rectangle vertexToCheck;
    int mouseButtonPressed, i;

    public static void main(String[] args) {
        //Eulerian PATH can have only 2 odd verticles - start at odd
        //Eulerian CIRCUIT can have only even verticles - start anywhere
        //If I have to choose i go for "no-bridge" edge (how?)

        new Main();
    }

    public Main(){
        this.setSize(400, 400);
        this.setTitle("Eulerian Path");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        Draw draw = new Draw();


        this.addMouseMotionListener(new MouseMotionAdapter() {
            //whats wrong: after clicking other vertex they merge together
            //
            @Override
            public void mouseDragged(MouseEvent e) {
                //super.mouseDragged(e);
                point.setLocation(e.getX() - 23, e.getY() - 45);
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
                start = new Point(e.getX() - 23, e.getY() - 45);
                vertexToCheck = new Rectangle(new Point(e.getX() - 23, e.getY() - 45), new Dimension(30, 30));
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

                end = new Point(e.getX() - 23, e.getY() - 45);
                //System.out.println("mouseReleaseButton: " + e.getButton());
                if((!intersects) & e.getButton() == MouseEvent.BUTTON1){
                    vertex = new Vertex(new Point(e.getX() - 23, e.getY() - 45)); // first created here
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

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        // Method to execute, initial delay, subsequent delay, time unit

        executor.scheduleAtFixedRate(new Repaint(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.add(panel);
        this.add(draw);
        this.setVisible(true);
    }
}