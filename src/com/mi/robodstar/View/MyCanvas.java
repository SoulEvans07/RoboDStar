package com.mi.robodstar.View;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Model.*;
import com.mi.robodstar.Model.Robot;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Abel on 2015. 12. 14..
 */
public class MyCanvas extends java.awt.Canvas{
    private Gui parent;
    TestChamber testChamber;
    private int width, height;
    private int lineWidth;
    private int tileSize;
    private Color lineColor;
    private Color wallColor;
    private Color fieldColor;
    public MyCanvas(Gui parent, TestChamber testChamber) {
        super();
        this.parent = parent;
        this.testChamber = testChamber;
        width = testChamber.getSize().getWidth();
        height = testChamber.getSize().getHeight();
        lineWidth=2;
        tileSize=16;
        lineColor = new Color(100,100,100);
        wallColor = new Color(238,236,225);
        fieldColor = new Color(87, 43,0);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
    }

    void drawMesh(Graphics g){
        int offset=tileSize+lineWidth;
        MPoint temp = new MPoint(0,0);
        Robot robot;
        ArrayList<Robot> robots = testChamber.getRobots();
        for(int i = 0; i<width; i++){
            for (int j = 0; j< height; j++){
                temp.set(i,j);
                if(testChamber.getChamber().isFree(temp)){
                    g.setColor(fieldColor);
                    g.drawRect(i*offset,j*offset,tileSize,tileSize);
                }
                else{
                    g.setColor(wallColor);
                    g.drawRect(i*offset,j*offset,tileSize,tileSize);
                }

                for(int k = 0; k < robots.size(); k++){
                    robot = robots.get(k);
                    if(robot.pos.equals(temp)){
                        g.setColor(robot.color);
                        g.drawRect(i*offset,j*offset,tileSize,tileSize);
                    }
                }
            }
        }
    }
}

