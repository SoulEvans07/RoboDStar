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

    public Robot(MPoint sPos, Color c){
        pos = sPos;
        color = c;

        radar = new MazeMap(Config.getFOV(), Config.getFOV());   // field of view: 5 wide 5 high
        hMap = new MazeMap(Config.getHeuriticMapPath());
    }

    public Robot(MPoint sPos){
        this(sPos, new Color(0,0,250));
    }

    public Robot(){
        this(new MPoint(0, 0), new Color(0, 0, 250));
    }



}
