package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Reference;
import com.mi.robodstar.Utility.LogHelper;

import java.util.ArrayList;

import static java.lang.Math.abs;


public class AStar extends Robot {
    private ArrayList<Node> open;
    private ArrayList<Node> closed;
    private Node at;
    public ArrayList<Boolean> pathBool;

    private Path path;

    public AStar(MazeMap map, String  heuritic, MPoint start, MPoint goal){
        super(map, heuritic, start, goal, Tile.ROBO1);
        initHeuristics();

        pathBool = new ArrayList<>();
        for(int y = 0; y < hMap.getSize().getHeight(); y++)
            for(int x = 0; x < hMap.getSize().getWidth(); x++)
                pathBool.add(false);
    }

    public void calc(){
        path = algorithmV2();
        LogHelper.comment("A* steps: " + path.steps.size());
    }

    public Path algorithmV2(){
        Node start = new Node(pos);
        start.setManhattanDist(goal);
        start.steps = 0;
        start.parent = null;

        NodeList open = new NodeList();
        NodeList closed = new NodeList();

        open.add(start);

        while(open.size() > 0){
            Node n = new Node(open.get(0));   // sorted list, first is the lowest cost

            if(n.pos.equals(goal))
                return new Path(n, hMap.getSize(), pos);
            open.remove(n);
            closed.add(n);

            // set neighbours
            NodeList neighbours = new NodeList(hMap.getValidNeighbours(n.pos));
            for(Node jason : neighbours.getList()){
                jason.setManhattanDist(goal);
                jason.steps = hMap.getSize().getWidth() * hMap.getSize().getHeight() + 1;   // "infinite"
                if(closed.contains(jason.pos)) {
                    jason.steps = closed.get(jason.pos).steps;
                }
                if(open.contains(jason.pos)) {
                    jason.steps = open.get(jason.pos).steps;
                }
            }

            for(Node jason : neighbours.getList()){
                boolean temp = (!open.contains(jason) && !closed.contains(jason)) ||  (n.dist + Reference.STEP < jason.steps);
                if(temp){
                    jason.steps = n.steps + Reference.STEP;
                    jason.parent = n;
                    open.add(jason);
                    closed.remove(jason);
                }
            }
        }
        LogHelper.error("No solution!");
        return null;
    }

    private void initHeuristics(){
        open = new ArrayList<>();
        closed = new ArrayList<>();
        at = new Node(pos);
        at.steps = 0;
        at.dist = abs(goal.getWidth() - pos.getWidth()) + abs(goal.getHeight() - pos.getHeight());

        open.add(at);
    }

    @Override
    public void tick() {
        MPoint temp = path.getNext(pos);
        if(path.getNext(pos) != null) {
            pos = temp;
        } else if(pos.equals(goal)) {

        } else
            LogHelper.error("Cannot reach");
    }

    @Override
    public boolean haveRadar() {
        return false;
    }
}
