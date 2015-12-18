package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

public abstract class Robot {
    protected MPoint pos;
    protected MPoint goal;
    protected MazeMap hMap;   // heuritic map
    private MazeMap chamber;  // actual map, private so the childes don't see it
    public int color;

    public Robot(MazeMap map, String heuritic, MPoint sPos, MPoint gPos, int state){
        chamber = map;
        pos = sPos;
        goal = gPos;
        color = state;
        hMap = new MazeMap(heuritic);
    }

    public MazeMap getRadar(){
        MazeMap radar = new MazeMap(Config.getFOVSize(), Config.getFOVSize());
        MPoint temp;
        int fov = Config.getFOV();
        for(int y = -fov; y <= fov; y++)
            for(int x = -fov; x <= fov; x++) {
                temp = new MPoint(pos.getWidth() + x, pos.getHeight() + y);
                if(!chamber.isOut(temp)) {
                    radar.add(chamber.getSimpleTile(temp.getWidth(), temp.getHeight()));
                } else{
                    radar.add(new Tile(Tile.OUT));
                }
            }
        return radar;
    }

    public MazeMap getHmap(){
        return hMap;
    }

    public abstract void tick();

    public abstract boolean haveRadar();
}

