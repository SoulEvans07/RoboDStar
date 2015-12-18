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

    public MazeMap getRadar(){
        MazeMap radar = new MazeMap(Config.getFOVSize(), Config.getFOVSize());
        MPoint temp;
        int fov = Config.getFOV();
        for(int y = -fov; y <= fov; y++)
            for(int x = -fov; x <= fov; x++) {
                temp = new MPoint(pos.getWidth() + x, pos.getHeight() + y);
                //temp.printPos(" x: " + x + " y: " + y + "\n");
                if(!chamber.isOut(temp)) {
                    radar.add(chamber.getTile(temp.getWidth(), temp.getHeight()));
                } else{
                    radar.add(new Tile(Tile.OUT));
                }
            }
        return radar;
    }

    public abstract void tick();

    public abstract boolean haveRadar();
}

