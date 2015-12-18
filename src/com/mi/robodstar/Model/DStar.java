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


    public void refreshView(){
        radar = getRadar();
        int fov = Config.getFOV();
        MPoint temp;
        int hState;
        int rState;
        for(int y = -fov; y <= fov; y++)
            for(int x = -fov; x <= fov; x++) {
                temp = new MPoint(pos.getWidth() + x, pos.getHeight() + y);
                //temp.printPos(" x: " + x + " y: " + y + "\n");
                if(!hMap.isOut(temp)) {
                    hState = hMap.getTile(temp.getWidth(), temp.getHeight()).getState();
                    rState = radar.getTile(x + fov, y + fov).getState();
                    if(hState != rState)
                        hMap.getTile(temp.getWidth(), temp.getHeight()).setState(rState);
                }
            }
        //hMap.printTiles2Console();
    }

    @Override
    public void tick() {
        refreshView();
    }

    @Override
    public boolean haveRadar() {
        return true;
    }
}
