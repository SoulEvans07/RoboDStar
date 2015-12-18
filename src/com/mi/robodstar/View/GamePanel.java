package com.mi.robodstar.View;

import com.mi.robodstar.Model.TestChamber;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MyCanvas canvas;
    private Gui parent;
    private int height;
    private int width;

    public GamePanel(Gui p, TestChamber testChamber){
        parent = p;
        setLayout(new BorderLayout());
        canvas = new MyCanvas(this, testChamber);
        this.add(canvas, BorderLayout.CENTER);
        canvas.paint(parent.getGraphics());
    }

    public void tick(){
        canvas.repaint();
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setWidth(int x){
        width=x;
    }
    public void setHeight(int y){
        height=y;
    }

}
