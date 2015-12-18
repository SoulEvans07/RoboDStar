package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

public abstract class Robot {
    protected MPoint pos;
    protected MPoint goal;
    protected MazeMap hMap;   // heuritic map
    private MazeMap chamber;  // actual map, private so the childes don't see it
    public int color;

    public Robot(MazeMap map, MPoint sPos, MPoint gPos, int state){
        chamber = map;
        pos = sPos;
        goal = gPos;
        color = state;
        hMap = new MazeMap(Config.getHeuriticMapPath());
    }

    public MazeMap getRadar(int fov){
        MazeMap radar = new MazeMap(fov, fov);
        MPoint temp;
        for(int y = 0; y < fov; y++)
            for(int x = 0; x < fov; x++) {
                temp = new MPoint(pos.getWidth() - fov + x, pos.getHeight() - fov + y);
                if(!hMap.isOut(temp))
                    radar.add(hMap.getTile(pos.getWidth() - fov + x, pos.getHeight() - fov + y));
            }
        return radar;
    }

    public abstract void tick();

    public abstract boolean haveRadar();
}

