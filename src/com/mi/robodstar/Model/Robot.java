package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

public abstract class Robot {
    protected MPoint pos;
    protected MPoint goal;
    protected MazeMap hMap;   // heuritic map
    public int color;

    // View specific

    public Robot(MPoint sPos, MPoint gPos, int state){
        pos = sPos;
        goal = gPos;
        color = state;
        hMap = new MazeMap(Config.getHeuriticMapPath());
    }

    public Robot(MPoint goal){
        this(new MPoint(0, 0), goal, Tile.ROBO1);
    }

    public abstract void tick();
}
