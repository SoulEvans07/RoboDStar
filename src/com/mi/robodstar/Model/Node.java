package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;

import static java.lang.Math.abs;


public class Node implements Comparable {
    public MPoint pos;
    public int dist;    // straight line distance (Manhattan distance)
    public int steps;   // steps to get to this node
    public Node parent;


    public Node(MPoint p){
        pos = p;
    }

    public Node(Node n){
        pos = new MPoint(n.pos);
        dist = n.dist;
        steps = n.steps;
        parent = n.parent;
    }

    public int getF(){
        return dist + steps;
    }

    public void setParent(Node par){
        parent = new Node(par);
    }

    public void setManhattanDist(MPoint goal){
        dist = abs(goal.getWidth() - pos.getWidth()) + abs(goal.getHeight() - pos.getHeight());
    }


    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Node)
        {
            sameSame = this.pos.equals(((Node) object).pos);
        }

        return sameSame;
    }

    public void printParent(){
        if(parent != null)
            parent.printParent();
        System.out.println("[" + pos.getWidth() + ", " + pos.getHeight() + "]");
    }



    @Override
    public int compareTo(Object o) {
        int compareF = ((Node)o).getF();

        return this.getF() - compareF;
    }
}