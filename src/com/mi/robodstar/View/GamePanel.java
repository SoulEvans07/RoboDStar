package com.mi.robodstar.View;

import com.mi.robodstar.Model.TestChamber;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Abel on 2015. 12. 14..
 */
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
        invalidate();
    }

    public void update(){
        canvas.paint(parent.getGraphics());
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
