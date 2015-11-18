package com.mi.robodstar.Model;

import com.mi.robodstar.Defaults.Reference;

import java.util.ArrayList;

public class TestChamber {
    private MazeMap chamber;    // the actual labyrinth
    private ArrayList<Robot> robots;

    public TestChamber(){
        chamber = new MazeMap(Reference.MAP_PATH); // init map
    }

    public void addRobot(Robot r){
        robots.add(r);
    }

    public void addRobot(){
        Robot temp = new Robot();
        robots.add(temp);
    }
}
