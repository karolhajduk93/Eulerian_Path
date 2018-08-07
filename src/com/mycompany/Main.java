package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame{

    public static ArrayList<Vertex> vertices = new ArrayList<>();
    Vertex vertex;
    boolean intersects = false;
    Rectangle vertexToCheck;
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

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {

                Rectangle rectangle1 = new Rectangle(new Point(138, 144), new Dimension(30, 30));
                Rectangle rectangle2 = new Rectangle(new Point(147, 127), new Dimension(30, 30));
                System.out.println(" asdasd" + rectangle1.intersects(rectangle2));
                // - X
                //if in bounds & connected get current node and boolean true
                //if not connected create connection start and other boolean true
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vertexToCheck = new Rectangle(new Point(e.getX() - 23, e.getY() - 45), new Dimension(30, 30));
                for (Vertex vertex: vertices){
                    if(vertex.getBounds().intersects(vertexToCheck)) {
                        intersects = true;
                        System.out.println("XXXXXXXXXXXXXX");
                        System.out.println(vertex.point.x +  " " + vertex.point.y);
                        break;
                    }
                }
                if((!intersects) & e.getButton() == MouseEvent.BUTTON1){
                    System.out.println(intersects);
                    System.out.println((e.getX() - 23) + " " + (e.getY() - 45));
                    vertex = new Vertex(new Point(e.getX() - 23, e.getY() - 45)); // first created here
                    vertices.add(vertex);
                    repaint();
                    //intersects = false;
                }
                else if(intersects & e.getButton() == MouseEvent.BUTTON3){
                    System.out.println("Right intersects");
                    intersects = false;
                }
                else if(intersects) {
                    System.out.println("no i chuj");
                    intersects = false;
                }
                //if boolean true set new location for current vertex
                //if other boolean set end point for connection
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

        executor.scheduleAtFixedRate(new Draw(), 0L, 20L, TimeUnit.MILLISECONDS);

        this.add(panel);
        this.add(draw);
        this.setVisible(true);
    }
}
