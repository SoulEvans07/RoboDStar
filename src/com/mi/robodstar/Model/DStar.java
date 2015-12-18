package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

public class DStar extends Robot {
    MazeMap radar;  // field of view

    public DStar(MazeMap map, MPoint start, MPoint goal){
        super(map, start, goal, Tile.ROBO2);
        radar = new MazeMap(Config.getFOVSize(), Config.getFOVSize());   // field of view: 5 wide 5 high
    }

    public MazeMap getRadarView(){
        return radar;
    }


    public void getView(){
        radar = getRadar();
        radar.printTiles2Console();
    }

    @Override
    public void tick() {
        // TODO: LATER!
    }

    @Override
    public boolean haveRadar() {
        return true;
    }
}
