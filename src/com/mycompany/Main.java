package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {

    HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    int path;
    boolean connected = true;

    public static void main(String[] args) {

        new Main();
    }

    public Main() {
        this.setSize(400, 400);
        this.setTitle("Eulerian Path");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        Draw draw = new Draw();
        JButton calculatePath = new JButton("CALCULATE PATH");
        calculatePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = 0;
                connected = true;
                //Storing info about connection between vertices

                for (Vertex v : draw.vertices) {
                    graph.put(v.number, new ArrayList<>());
                }
                for (Line line : draw.lines) {
                    if (!graph.containsKey(line.startVertex)) {
                        graph.put(line.startVertex, new ArrayList<>());
                        if (!graph.get(line.startVertex).contains(line.endVertex))
                            graph.get(line.startVertex).add(line.endVertex);
                    } else if (graph.containsKey(line.startVertex) && (!graph.get(line.startVertex).contains(line.endVertex)))
                        graph.get(line.startVertex).add(line.endVertex);

                    if (!graph.containsKey(line.endVertex)) {
                        graph.put(line.endVertex, new ArrayList<>());
                        if (!graph.get(line.endVertex).contains(line.startVertex))
                            graph.get(line.endVertex).add(line.startVertex);
                    } else if (graph.containsKey(line.endVertex) && !graph.get(line.endVertex).contains(line.startVertex))
                        graph.get(line.endVertex).add(line.startVertex);
                }
                System.out.println(graph.toString());

                //Depth-first Search - checking if graph is connected
                boolean visited[] = new boolean[Vertex.iterator];
                if (!graph.isEmpty())
                    DFS(1, visited);
                for (boolean b : visited) {
                    if (!b) {
                        connected = false;
                        break;
                    }
                }

                //Checking if graph has Eulerian path or circuit

                //Eulerian PATH must have exactly 2 odd vertices  - start at odd
                //Eulerian CIRCUIT can have only even vertices - start anywhere

                for(ArrayList<Integer> list : graph.values()){

                    if(list.size() % 2 == 1)
                        path++;
                }
                if(path == 0 && connected)
                    System.out.println("circuit");
                else if(path == 2 && connected)
                    System.out.println("path");
                else
                    System.out.println("Not eulerian");
            }
        });
        panel.add(calculatePath);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        // Method to execute, initial delay, subsequent delay, time unit
        executor.scheduleAtFixedRate(new Repaint(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.add(draw, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    void DFS(int v, boolean visited[]) {
        // Mark the current node as visited
        visited[v - 1] = true;

        if ((!graph.get(v).isEmpty()))
            for (Integer i : graph.get(v))
                if (!(visited[i - 1]))
                    DFS(i, visited);
    }
}
    // The function to do DFS traversal. It uses recursive DFSUtil()
