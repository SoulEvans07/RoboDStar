package com.mi.robodstar.Model;

import java.awt.*;

public class Tile {
    private int state;

    public Tile(int startState){
        state = startState;
    }

    public void setState(int s){
        state=s;
    }
    public int getState(){
        return state;
    }

    public Color getColor(){
        Color ret;
        switch (state){
            case OBSTACLE:
                ret = wallColor;
                break;
            case FREE:
                ret = freeColor;
                break;
            case ROBO1:
                ret = robo1Color;
                break;
            case ROBO1_TRACE:
                ret = robo1TraceColor;
                break;
            case ROBO2:
                ret = robo2Color;
                break;
            case ROBO2_TRACE:
                ret = robo2TraceColor;
                break;
            case JOINT_TRACE:
                ret = jointTraceColor;
                break;
            case GOAL:
                ret = goalColor;
                break;
            default:
                ret = new Color(0, 255 , 0);
                break;
        }
        return ret;
    }

    public static final char START_CHAR = 'S';

    public static final int OBSTACLE = 0;
    public static final Color wallColor = new Color(43, 43, 43);
    public static final char OBST_CHAR = '#';

    public static final int FREE = 1;
    public static final Color freeColor = new Color(238,236,225);
    public static final char FREE_CHAR = '_';

    public static final int ROBO1 = 2;
    public static final Color robo1Color = new Color(255, 0, 0);

    public static final int ROBO1_TRACE = 3;
    public static final Color robo1TraceColor = new Color(255, 160, 160);

    public static final int ROBO2 = 4;
    public static final Color robo2Color = new Color(0, 0, 255);

    public static final int ROBO2_TRACE = 5;
    public static final Color robo2TraceColor = new Color(160, 160, 255);

    public static final int JOINT_TRACE = 6;
    public static final Color jointTraceColor = new Color(211, 151, 255);

    public static final int GOAL = 7;
    public static final Color goalColor = new Color(239, 239, 0);
    public static final char GOAL_CHAR = 'G';
}
