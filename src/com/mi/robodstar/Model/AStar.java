package com.mi.robodstar.Model;

import Tests.TestGui;
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

    public AStar(MPoint start, MPoint goal){
        super(start, goal, Tile.ROBO1);
        initHeuristics();

        pathBool = new ArrayList<>();
        for(int y = 0; y < hMap.getSize().getHeight(); y++)
            for(int x = 0; x < hMap.getSize().getWidth(); x++)
                pathBool.add(false);
    }

    public void calc(){
        path = algorithmV2();
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
            Node n = open.get(0);   // sorted list, first is the lowest cost

            if(n.pos.equals(goal))
                return new Path(n, hMap.getSize(), pos);
            open.remove(n);
            closed.add(n);

            // set neighbours
            NodeList neighbours = new NodeList(hMap.getValidNeighbours(n.pos));
            for(Node jason : neighbours.getList()){
                jason.setManhattanDist(goal);
                jason.steps = hMap.getSize().getWidth() * hMap.getSize().getHeight();
                if(open.contains(jason.pos)) {
                    jason.steps = open.get(jason.pos).steps;
                }
                if(closed.contains(jason.pos)) {
                    jason.steps = closed.get(jason.pos).steps;
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

    public void algorithm(){
        Node loc = at;
        ArrayList<Node> neighbours;

        //LogHelper.comment("Start: loc[" + loc.pos.getWidth() + ", " + loc.pos.getHeight() + "]");
        while(open.size() != 0 && !pos.equals(goal)) {
            neighbours = hMap.getValidNeighbours(loc.pos);
            for(int i = 0; i < closed.size(); i++){
                while (neighbours.contains(closed.get(i))){
                    neighbours.remove(closed.get(i));
                }
            }
            while(open.contains(loc))
                open.remove(loc);

            if(closed.contains(loc) == false) {
                closed.add(new Node(loc));
            }
            /*
            if(neighbours.size() != 0) {
                int k = 0;
                int min = neighbours.get(k).getF();
                for (int i = 1; i < neighbours.size(); i++) {
                    if(neighbours.get(i).getF() < min){
                        min = neighbours.get(i).getF();
                        k = i;
                    }
                }
            }
            */

            //LogHelper.inline(">>>> neighbours[" + neighbours.size() + "]:");
            Node neighbour;
            for (int i = 0; i < neighbours.size(); i++) {
                MPoint p = neighbours.get(i).pos;
                //LogHelper.inline(i + ".pos[" + p.getWidth() + ", " + p.getHeight() + "]");
                neighbours.get(i).steps = loc.steps + 1;
                neighbours.get(i).dist = abs(goal.getWidth() - p.getWidth()) + abs(goal.getHeight() - p.getHeight());
                neighbour = new Node(neighbours.get(i));
                neighbour.setParent(loc);
                open.add(neighbour);
            }
            //LogHelper.inline("<<<< neighbours");

            int k = 0;
            int min = open.get(k).getF();
            for (int i = 1; i < open.size(); i++) {
                if(open.get(i).getF() < min){
                    min = open.get(i).getF();
                    k = i;
                }
            }

            /*LogHelper.inline(">>>> open[" + open.size() + "]:");
            for (int i = 0; i < open.size(); i++) {
                LogHelper.inline("open[" + i + "] = [" + open.get(i).pos.getWidth() + ", " + open.get(i).pos.getHeight() + "]");
            }
            LogHelper.inline("<<<< open");
            LogHelper.breakLine(1);
            LogHelper.inline(">>>> closed[" + closed.size() + "]:");
            for (int i = 0; i < closed.size(); i++) {
                LogHelper.inline("closed[" + i + "] = [" + closed.get(i).pos.getWidth() + ", " + closed.get(i).pos.getHeight() + "]");
            }
            LogHelper.inline("<<<< closed");*/

            Node temp = new Node(open.get(k));
            //temp.setParent(loc);
            loc = temp;
            //TODO:!!!!!!!!!!!!!!!!!!!!!!!
            pos.set(loc.pos.getWidth(), loc.pos.getHeight());
            //LogHelper.comment("Next: pos[" + pos.getWidth() + ", " + pos.getHeight() + "]");
            //LogHelper.inline("-----------------------------------");
            TestGui.tick();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //loc.printParent();
        Node temp = new Node(loc);
        while(temp != null){
            pathBool.set(temp.pos.getHeight()*hMap.getSize().getWidth() + temp.pos.getWidth(), true);
            temp = temp.parent;
        }

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
        if(temp != null) {
            pos = temp;
        } else if(pos.equals(goal)) {
            LogHelper.inline("WIN");
            Clock.stopClock();
        } else
            LogHelper.error("Cannot reach");
    }
}
