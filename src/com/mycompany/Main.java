package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {

    HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    int path;
    boolean connected = true;
    String resultString = "";

    ArrayList<Integer> pathOrCircuit = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();
    int location;

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

                for(Map.Entry<Integer, ArrayList<Integer>> graph : graph.entrySet()){


                    if(graph.getValue().size() % 2 == 1) {
                        location = graph.getKey();
                        //System.out.println(location);
                        path++;
                    }
                }
                if(path == 0 && connected){
                    location = draw.vertices.get(new Random().nextInt(Vertex.iterator)).number;
                    findEulerianPath();
                    //System.out.println("circuit" + location);
                }
                else if(path == 2 && connected) {
                    //System.out.println("path" + location);
                    findEulerianPath();
                }
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

    public void DFS(int v, boolean visited[]) {
        // Mark the current node as visited
        visited[v - 1] = true;

        if ((!graph.get(v).isEmpty()))
            for (Integer i : graph.get(v))
                if (!(visited[i - 1]))
                    DFS(i, visited);
    }

    public void findEulerianPath(){
        // Algoritm for finding Eulerian Path

        /*
        location = 2
        stack = [2]
        tmp = get->[2](0) = 1
        remove [2](0) -> 1
        .. remove [tmp/1](0)-> 2
        */
        pathOrCircuit.clear();

        int i = 0;
        while(!(graph.get(location).isEmpty() && stack.isEmpty())) {
            int tmp;
            i++;
            if ( i > 100)
                break;
            //System.out.print(" g: " + graph.get(location).isEmpty() + " s: " + stack.isEmpty());
            System.out.println("STACK" + stack.toString());

            if (!graph.get(location).isEmpty()) {
                stack.push(location);
                System.out.println("A");
                tmp = graph.get(location).get(0);  // bierzemy
                graph.get(location).remove(0); // i usuwamy pierwszego "sasiada" z brzegu [3]-> [2] tmp = 2
                System.out.println(location + "=" + graph.get(location).toString());
                int k = 0;
               for(Integer j: graph.get(tmp)){
                   if(j == location) {
                       break;
                   }
                   k++;
               }

                graph.get(tmp).remove(k);
               // czyli nie zero tylko odpowaidajace || musimy wziac z [2]->[3] a nie [1] jak teraz ????????????? 3
                System.out.println(tmp + "=" + graph.get(tmp).toString());
                location = tmp;
            } else if (graph.get(location).isEmpty() && !stack.isEmpty()) {
                System.out.println("B");
                pathOrCircuit.add(location);
                location = stack.pop();
            }
            if (graph.get(location).isEmpty() && stack.isEmpty()) {
                System.out.println("C");
                pathOrCircuit.add(location);
            }
        }
        //System.out.println("\n" + stack.toString());
        System.out.println(pathOrCircuit.toString());
        resultString = pathOrCircuit.toString();



    }
}
