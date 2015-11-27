package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Defaults.Reference;

import java.util.ArrayList;

public class TestChamber {
    private MazeMap chamber;    // the actual labyrinth
    private ArrayList<Robot> robots;
    private MPoint goal;

    public TestChamber(){
        chamber = new MazeMap(Config.getMapPath()); // init map
        goal = chamber.searchPoint(Reference.GOAL_TILE);
    }

    public void addRobot(Robot r){
        r.pos = chamber.searchPoint(Reference.START_TILE);
        robots.add(r);
    }

    public void addRobot(){
        Robot temp = new Robot();
        temp.pos = chamber.searchPoint(Reference.START_TILE);
        robots.add(temp);
    }
}
