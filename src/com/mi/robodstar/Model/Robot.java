package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Defaults.Reference;

public class Robot {
    MPoint pos;
    MazeMap radar;  // field of view
    MazeMap hMap;   // heuritic map


    public Robot(){
        radar = new MazeMap(Config.FOV, Config.FOV);   // field of view: 5 wide 5 high
        hMap = new MazeMap(Reference.HMAP_PATH);
    }

    public Robot(MPoint sPos){
        super();
        pos = sPos; // set starting point
    }

}
