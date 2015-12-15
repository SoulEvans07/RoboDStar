package com.mi.robodstar.Model;

import Tests.TestGui;
import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Utility.LogHelper;

import java.util.ArrayList;

import static java.lang.Math.abs;


public class AStar extends Robot {
    private ArrayList<Node> open;
    private ArrayList<Node> closed;
    private Node at;

    public AStar(MPoint start, MPoint goal){
        super(start, goal);
        initHeuristics();
    }

    public void algorithm(){
        Node loc = at;
        ArrayList<Node> neighbours;

        LogHelper.comment("Start: loc[" + loc.pos.getWidth() + ", " + loc.pos.getHeight() + "]");
        while(open.size() != 0) {
            neighbours = hMap.getValidNeighbours(loc.pos);
            open.remove(loc);
            closed.add(new Node(loc));
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
            for (int i = 0; i < neighbours.size(); i++) {
                MPoint p = neighbours.get(i).pos;
                //LogHelper.inline(i + ".pos[" + p.getWidth() + ", " + p.getHeight() + "]");
                neighbours.get(i).steps = loc.steps + 1;
                neighbours.get(i).dist = abs(goal.getWidth() - p.getWidth()) + abs(goal.getHeight() - p.getHeight());
                open.add(neighbours.get(i));
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
            //{
            LogHelper.inline(">>>> open[" + open.size() + "]:");
            for (int i = 0; i < open.size(); i++) {
                LogHelper.inline("open[" + i + "] = [" + open.get(i).pos.getWidth() + ", " + open.get(i).pos.getHeight() + "]");
            }
            LogHelper.inline("<<<< open");
            LogHelper.breakLine(1);
            LogHelper.inline(">>>> closed[" + closed.size() + "]:");
            for (int i = 0; i < closed.size(); i++) {
                LogHelper.inline("closed[" + i + "] = [" + closed.get(i).pos.getWidth() + ", " + closed.get(i).pos.getHeight() + "]");
            }
            LogHelper.inline("<<<< closed");
            //}
            loc = open.get(k);
            //TODO:!!!!!!!!!!!!!!!!!!!!!!!
            pos.set(loc.pos.getWidth(), loc.pos.getHeight());
            LogHelper.comment("Next: pos[" + pos.getWidth() + ", " + pos.getHeight() + "]");
            TestGui.tick();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    }
}
