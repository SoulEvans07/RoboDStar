package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;

import java.util.ArrayList;

public class Path {
    private MPoint size;
    private ArrayList<Node> steps;
    private ArrayList<Boolean> path;

    public Path(Node goal, MPoint size){
        this.size = size;
        this.steps = new ArrayList<>();
        this.path = new ArrayList<>();
        for(int y = 0; y < size.getHeight(); y++)
            for(int x = 0; x < size.getWidth(); x++)
                this.path.add(false);

        Node temp = new Node(goal);
        ArrayList<Node> t = new ArrayList<>();
        while(temp != null){
            t.add(temp);
            path.set(temp.pos.getHeight() * size.getWidth() + temp.pos.getWidth(), true);
            temp = temp.parent;
        }

        for(int i = t.size()-1; i >= 0; i--)
            steps.add(t.get(i));
    }

    public ArrayList<Boolean> getSolution(){
        return path;
    }

    public MPoint getNext(MPoint pos){
        for(Node n : steps){
            if(n.pos.equals(pos))
                if(steps.indexOf(n)+1 < steps.size())
                return steps.get(steps.indexOf(n)+1).pos;
        }
        return null;
    }


    public void printPath(){
        for(Node n : steps)
            System.out.println("[" + n.pos.getWidth() + ", " + n.pos.getHeight() + "]");
    }
}
