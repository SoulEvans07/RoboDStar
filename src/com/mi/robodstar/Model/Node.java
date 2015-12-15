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
        pos = new MPoint(n.pos);
        dist = n.dist;
        steps = n.steps;
    }

    public int getF(){
        return dist + steps;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Node)
        {
            sameSame = this.pos.equals(((Node) object).pos);
        }

        return sameSame;
    }
}
