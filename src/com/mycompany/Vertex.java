package com.mycompany;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Vertex extends Polygon{
    final int height = 30;
    final int width = 30;
    Point point;
    boolean isConnected;
    Shape circle;
    int number;

    static int iterator = 0;

    public Vertex(Point point){
        this.point = point;
        circle = new Ellipse2D.Double(point.x, point.y, 30 ,30);
        iterator++;
        number = iterator;
        isConnected = false;
    }

    public Rectangle getBounds(){
        return new Rectangle(point.x , point.y , width, height);
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}