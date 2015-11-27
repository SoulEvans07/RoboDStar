package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Utility.LogHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MazeMap {
    private ArrayList<Boolean> tiles;       // true = free spot, false = obstacle
    private ArrayList<String> map;
    private MPoint size;


    // empty map
    public MazeMap(int width, int height){
        this.size = new MPoint(width, height);
        this.tiles = new ArrayList<>();
    }

    // empty map
    public MazeMap(MPoint size) {
        this(size.getWidth(), size.getHeight());
    }

    // map from txt
    public MazeMap(String mapFilePath) {
        tiles = new ArrayList<Boolean>();

        String line;
        BufferedReader br = null;
        try {
            InputStream is = this.getClass().getResourceAsStream(mapFilePath);
            InputStreamReader ir = new InputStreamReader(is);
            br = new BufferedReader(ir);

            int row = 0;
            while ((line = br.readLine()) != null) {
                map.add(line);  // save txt into array so we don't have to open it after
                for (int i = 0; i < line.length(); i++) {
                    boolean temp = false;
                    if (line.charAt(i) == '0')
                        temp = true;
                    tiles.add(temp);
                }
                row++;
            }
            size.set(line.length(), row);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public MPoint searchPoint(char c){
        MPoint goal = new MPoint(0, 0);
        for(int y = 0; y < size.getHeight(); y++)
            for(int x = 0; x < size.getWidth(); x++)
                if(map.get(y).charAt(x) == c)
                    goal.set(x, y);
        return goal;
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
