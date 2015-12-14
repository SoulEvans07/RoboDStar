package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Defaults.Reference;
import com.mi.robodstar.Utility.LogHelper;

import java.util.ArrayList;

public class TestChamber {
    private MazeMap chamber;    // the actual labyrinth
    private ArrayList<Robot> robots;
    private MPoint goal;

    public TestChamber(){
        robots = new ArrayList<>();
        chamber = new MazeMap(Config.getMapPath()); // init map
        goal = chamber.searchPoint(Reference.GOAL_TILE);
        LogHelper.error(goal.getWidth() + " " + goal.getHeight());
    }

    public MPoint getSize(){
        return chamber.getSize();
    }

    public void addRobot(Robot r){
        r.pos = chamber.searchPoint(Reference.START_TILE);
        robots.add(r);
    }

    public ArrayList<Robot> getRobots(){
        return robots;
    }

    public MazeMap getChamber() {
        return chamber;
    }

    public MPoint getGoal(){
        LogHelper.error("gg"+goal.getWidth() + " " + goal.getHeight());
        return goal;
    }
}
