package com.mi.robodstar.Model;

public class DNode {
    public int posx;
    public int posy;

    public int gScore;
    public double hValue;
    public boolean obstacle;
    public boolean pathNode;
    public int ID;
    public int tag;
    public double key;

    public int backPointer;

    public static final int NEW = 0;
    public static final int OPEN = 1;
    public static final int CLOSED = 2;

    public static final int COST = 1;
}
