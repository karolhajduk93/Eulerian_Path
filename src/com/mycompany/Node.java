package com.mycompany;

import java.awt.*;

public class Node extends Polygon{
    Point point;
    final int height = 10;
    final int width = 10;
    boolean isConnected = false;

    public Node(Point point){
        this.point = point;
    }

    public Rectangle getBounds(){
        return new Rectangle(point.x, point.y, width, height);
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
