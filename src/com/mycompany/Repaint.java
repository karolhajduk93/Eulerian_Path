package com.mycompany;

public class Repaint implements Runnable {

    Main main;

    public Repaint (Main main){
        this.main = main;
    }
    @Override
    public void run() {
        main.repaint();
    }
}
