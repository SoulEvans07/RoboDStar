package com.mi.robodstar.View;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Model.*;
import com.mi.robodstar.Model.Robot;
import com.mi.robodstar.Utility.LogHelper;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Abel on 2015. 12. 14..
 */
public class MyCanvas extends java.awt.Canvas{
    private GamePanel parent;
    TestChamber testChamber;
    private int cols, rows;
    private int lineWidth;
    private int tileSize;
    private Color lineColor;
    private Color wallColor;
    private Color fieldColor;
    private Color goalColor;
    public MyCanvas(GamePanel parent, TestChamber testChamber) {
        super();
        this.parent = parent;
        this.testChamber = testChamber;
        cols = testChamber.getSize().getWidth();
        rows = testChamber.getSize().getHeight();
        lineWidth=2;
        tileSize=16;
        lineColor = new Color(100,100,100);
        fieldColor = new Color(238,236,225);
        wallColor = new Color(87, 43,0);
        goalColor = new Color(200,200,0);
        LogHelper.error(cols);
        LogHelper.error(rows);
        parent.setHeight(rows*(lineWidth+tileSize)+lineWidth);
        parent.setWidth(cols*(lineWidth+tileSize)+lineWidth);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        drawMaze(g2);
    }

    void drawMaze(Graphics2D g){
        int offset=tileSize+lineWidth;
        MPoint temp = new MPoint(0,0);
        Robot robot;
        ArrayList<Robot> robots = testChamber.getRobots();
        g.setColor(lineColor);
        g.fillRect(0,0,offset*cols+lineWidth,offset*rows+lineWidth);
        Color tempC = new Color(0,0,0);
        for(int i = 0; i<cols; i++){
            for (int j = 0; j< rows; j++){
                temp.set(i,j);
                if(testChamber.getChamber().isFree(temp)){
                    tempC=fieldColor;
                }
                else{
                    tempC=wallColor;
                }

                if(testChamber.getGoal().equals(temp)){
                //if(testChamber.getGoal().getWidth()==i && testChamber.getGoal().getHeight()==j ){
                    tempC = goalColor;
                }

                for(int k = 0; k < robots.size(); k++){
                    robot = robots.get(k);
                    if(robot.pos.equals(temp)){
                        tempC = robot.color;
                    }
                }
                g.setColor(tempC);
                g.fillRect(lineWidth+i*offset,lineWidth+j*offset,tileSize,tileSize);
            }
        }
    }

}

