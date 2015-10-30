package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Utility.LogHelper;

import java.util.ArrayList;

public class MazeMap {
    private ArrayList<Boolean> tiles;       // true = free spot, false = obstacle
    private MPoint size;

    public MazeMap(int width, int height){
        this.size = new MPoint(width, height);
        this.tiles = new ArrayList<>();
    }

    public MazeMap(MPoint size) {
        this(size.getWidth(), size.getHeight());
    }

    public MPoint getSize() {
        return size;
    }

    public boolean isOut(MPoint p){
        return (p.getWidth() < 0) || (p.getWidth() > size.getWidth()) ||
                (p.getHeight() < 0) || (p.getHeight() > size.getHeight());
    }

    public boolean isFree(MPoint p){
        if(isOut(p))
            LogHelper.error("Out of Map");
        return tiles.get(p.getHeight() * size.getWidth() + p.getWidth());
    }
}
