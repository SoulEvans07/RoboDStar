package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

import java.util.ArrayList;

public class TestChamber {
    private MazeMap chamber;    // the actual labyrinth
    private ArrayList<Robot> robots;
    private MPoint goal;

    public TestChamber(){
        robots = new ArrayList<>();
        chamber = new MazeMap(Config.getMapPath()); // init map
        goal = chamber.searchPoint(Tile.GOAL_CHAR);
    }

    public MPoint getSize(){
        return chamber.getSize();
    }

    public void addRobot(Robot r){
        r.pos = chamber.searchPoint(Tile.START_CHAR);
        robots.add(r);

        int index = robots.indexOf(r);
        if (index == 0){
            chamber.getTile(r.pos.getWidth(),r.pos.getHeight()).setState(Tile.ROBO1);
        }
        if (index == 1){
            chamber.getTile(r.pos.getWidth(),r.pos.getHeight()).setState(Tile.ROBO2);
        }
    }

    public ArrayList<Robot> getRobots(){
        return robots;
    }

    public MazeMap getChamber() {
        return chamber;
    }

    public MPoint getGoal(){
        return goal;
    }
}
