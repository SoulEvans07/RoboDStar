package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;


public class Node {
    public MPoint pos;
    public int dist;    // straight line distance (Manhattan distance)
    public int steps;   // steps to get to this node

    public Node(MPoint p){
        pos = p;
    }

    public Node(Node n){
        pos = n.pos;
        dist = n.dist;
        steps = n.steps;
    }

    public int getF(){
        return dist + steps;
    }

}
