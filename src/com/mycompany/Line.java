package com.mycompany;

import javafx.scene.shape.Shape;

import java.awt.*;
import java.awt.geom.Line2D;


public class Line{
    int startVertex;
    int endVertex;

    Line2D line;

    public Line(Point p1, Point p2){
        line = new Line2D.Float();
        line.setLine(p1, p2);
    }
}


