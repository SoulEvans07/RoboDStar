package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;

public class DStar extends Robot {
    MazeMap radar;  // field of view

    public DStar(MPoint start, MPoint goal){
        super(start, goal);
        radar = new MazeMap(Config.getFOV(), Config.getFOV());   // field of view: 5 wide 5 high
    }

    @Override
    public void tick() {
        // TODO: LATER!
    }
}
