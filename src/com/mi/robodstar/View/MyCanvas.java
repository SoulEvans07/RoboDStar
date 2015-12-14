package com.mi.robodstar.View;

import com.mi.robodstar.Model.TestChamber;

import java.awt.*;

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
    public MyCanvas(Gui parent, TestChamber testChamber) {
        super();
        this.parent = parent;
        this.testChamber = testChamber;
        width = testChamber.getSize().getWidth();
        height = testChamber.getSize().getHeight();
        lineWidth=2;
        tileSize=16;
        lineColor = new Color(100,100,100);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        drawMaze(g2);
    }

    void drawMaze(Graphics2D g){
        /*int offset=tileSize+lineWidth;
        MPoint temp = new MPoint(0,0);
        Robot robot;
        ArrayList<Robot> robots = testChamber.getRobots();
        g.setColor(lineColor);
        g.fillRect(0,0,offset*width,offset*height);
        Color tempC = new Color(0,0,0);
        for(int i = 0; i<width; i++){
            for (int j = 0; j< height; j++){
                temp.set(i,j);
                LogHelper.error("---" + i + " " + j);
                if(testChamber.getChamber().isFree(temp)){
                    tempC=fieldColor;
                }
                else{
                    tempC=wallColor;
                }

                if(testChamber.getGoal().equals(temp)){
                    tempC = goalColor;
                }

                for(int k = 0; k < robots.size(); k++){
                    robot = robots.get(k);
                    if(robot.pos.equals(temp)){
                        tempC = robot.color;
                    }
                }
                g.setColor(tempC);
                g.fillRect(i*offset,j*offset,tileSize,tileSize);
            }
        }*/
    }
}

