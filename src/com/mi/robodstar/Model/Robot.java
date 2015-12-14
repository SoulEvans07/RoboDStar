package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

import java.awt.*;

public class Robot {
    public MPoint pos;
    MazeMap radar;  // field of view
    MazeMap hMap;   // heuritic map

    // View specific
    public Color color;

    public Robot(){
        pos = new MPoint(0, 0);
        radar = new MazeMap(Config.getFOV(), Config.getFOV());   // field of view: 5 wide 5 high
        hMap = new MazeMap(Config.getHeuriticMapPath());
    }

    public Robot(MPoint sPos){
        super();
        pos = sPos; // set starting point
    }



}
