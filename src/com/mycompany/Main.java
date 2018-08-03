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
                /*int index = 0;
                for (Node node: nodes){
                    if(node.getBounds().contains(new Point(e.getX() - 10, e.getY() - 30))) {
                        intersects = true;
                        break;
                    }
                    index++;
                }
                System.out.println(index); // ------------------
                System.out.println("(x): " + e.getX() + " (y): " + e.getX());
                if(!nodes.isEmpty()) {
                    if (intersects & nodes.get(index-1).isConnected ){
                        nodes.get(index).setPoint(new Point(e.getX() - 13, e.getY() - 35));
                    }
                }*/
                // - X
                //if in bounds & connected get current node and boolean true
                //if not connected create connection start and other boolean true
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for (Vertex node: vertices){
                if(node.getBounds().contains(e.getLocationOnScreen())) {
                    intersects = true;
                    break;
                }
            }
                if(!intersects){
                    vertex = new Vertex(new Point(e.getX() - 23, e.getY() - 45));
                    vertices.add(vertex);
                    repaint();
                }
                //if boolean true set new location for current node
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
