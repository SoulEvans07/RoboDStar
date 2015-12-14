package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

import java.awt.*;

public class Robot {
    public MPoint pos;
    MazeMap radar;  // field of view
    MazeMap hMap;   // heuritic map

    // View specific

    public Robot(MPoint sPos){
        pos = sPos;

        radar = new MazeMap(Config.getFOV(), Config.getFOV());   // field of view: 5 wide 5 high
        hMap = new MazeMap(Config.getHeuriticMapPath());
    }

    public Robot(){
        this(new MPoint(0, 0));
    }
}
