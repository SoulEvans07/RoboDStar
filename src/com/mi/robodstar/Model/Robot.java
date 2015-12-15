package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

public abstract class Robot {
    public MPoint pos;
    protected MPoint goal;
    protected MazeMap hMap;   // heuritic map

    // View specific

    public Robot(MPoint sPos, MPoint gPos){
        pos = sPos;
        goal = gPos;
        hMap = new MazeMap(Config.getHeuriticMapPath());
    }

    public Robot(MPoint goal){
        this(new MPoint(0, 0), goal);
    }

    public abstract void tick();
}
