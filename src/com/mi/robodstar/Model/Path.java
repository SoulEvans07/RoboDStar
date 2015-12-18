package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;

import java.util.ArrayList;

public class Path {
    private MPoint size;
    private ArrayList<MPoint> steps;
    private ArrayList<Boolean> path;

    public Path(Node goal, MPoint size, MPoint pos){
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
            steps.add(new MPoint(t.get(i).pos));
    }

    public ArrayList<Boolean> getSolution(){
        return path;
    }

    public MPoint getNext(MPoint pos){
        for(int i = 0; i < steps.size(); i++){
            if(steps.get(i).equals(pos))
                if(i+1 < steps.size()) {
                    return steps.get(i + 1);
                }
        }
        return null;
    }


    public void printPath(int to){
        for(int i = 0; i < steps.size() && i < to; i++)
            steps.get(i).printPos();
    }

    public void printPath(){
        printPath(steps.size());
    }
}
