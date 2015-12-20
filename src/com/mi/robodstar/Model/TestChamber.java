package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

import java.util.ArrayList;

public class TestChamber {
    private MazeMap chamber;    // the actual labyrinth
    private ArrayList<Robot> robots;
    private MPoint goal;
    private MPoint startPoint;

    public TestChamber(int test){
        robots = new ArrayList<>();
        chamber = new MazeMap(Config.getMapPath(test)); // init map
        goal = chamber.searchPoint(Tile.GOAL_CHAR);
        startPoint = chamber.searchPoint(Tile.START_CHAR);
    }

    public MPoint getSize(){
        return chamber.getSize();
    }

    public void addRobot(Robot r){
        robots.add(r);
        chamber.getTile(r.pos.getWidth(),r.pos.getHeight()).steppedOn(r.color);
        r.getHmap().getTile(r.pos.getWidth(),r.pos.getHeight()).steppedOn(r.color);
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

    public MPoint getStart(){
        return startPoint;
    }

    public void tick() {
        for(Robot r : robots) {
            chamber.getTile(r.pos.getWidth(), r.pos.getHeight()).fade();
            r.getHmap().getTile(r.pos.getWidth(), r.pos.getHeight()).fade();
        }
        for(Robot r : robots) {
            r.tick();
            chamber.getTile(r.pos.getWidth(), r.pos.getHeight()).steppedOn(r.color);
            r.getHmap().getTile(r.pos.getWidth(), r.pos.getHeight()).steppedOn(r.color);
        }

        /*
        for(Robot r : robots)
            r.tick();

        for(int y = 0; y < chamber.getSize().getHeight(); y++)
            for (int x = 0; x < chamber.getSize().getWidth(); x++){
                if(chamber.getTile(x, y).getState() == Tile.ROBO1)
                    chamber.getTile(x, y).setState(Tile.ROBO1_TRACE);
            }

        for(int i = 0; i < robots.size(); i++){
            if (i == 0){
                //LogHelper.comment("Color: pos[" + robots.get(0).pos.getWidth() + ", " + robots.get(0).pos.getHeight() + "]");
                //LogHelper.breakLine(1);
                chamber.getTile(robots.get(i).pos.getWidth(),robots.get(i).pos.getHeight()).setState(Tile.ROBO1);
            }
            if (i == 1){
                chamber.getTile(robots.get(i).pos.getWidth(),robots.get(i).pos.getHeight()).setState(Tile.ROBO2);
            }
        }*/
    }
}
