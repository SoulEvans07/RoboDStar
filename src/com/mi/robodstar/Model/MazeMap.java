package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Utility.LogHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MazeMap {
    private ArrayList<Tile> tiles;       // true = free spot, false = obstacle
    private ArrayList<String> map;
    private MPoint size;


    // empty map
    public MazeMap(int width, int height){
        this.size = new MPoint(width, height);
        this.tiles = new ArrayList<>();
        this.map = new ArrayList<>();
    }

    // empty map
    public MazeMap(MPoint size) {
        this(size.getWidth(), size.getHeight());
    }

    // map from txt
    public MazeMap(String mapFilePath) {
        this.tiles = new ArrayList<Tile>();
        this.map = new ArrayList<>();


        String line;
        BufferedReader br = null;
        try {
            InputStream is = this.getClass().getResourceAsStream(mapFilePath);
            InputStreamReader ir = new InputStreamReader(is);
            br = new BufferedReader(ir);

            int row = 0;
            int length = 0;
            while ((line = br.readLine()) != null) {
                map.add(line);  // save txt into array so we don't have to open it after
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)){
                        case Tile.START_CHAR:
                            tiles.add(new Tile(Tile.FREE));
                            break;
                        case Tile.FREE_CHAR:
                            tiles.add(new Tile(Tile.FREE));
                            break;
                        case Tile.OBST_CHAR:
                            tiles.add(new Tile(Tile.OBSTACLE));
                            break;
                        case Tile.GOAL_CHAR:
                            tiles.add(new Tile(Tile.GOAL));
                            break;
                        default:
                            System.out.println("f*ck this char: " + line.charAt(i));
                            break;
                    }
                }
                if(length != 0 && line.length() != length)
                    LogHelper.error("Hibas sor: " + row+1);
                else
                    length = line.length();
                row++;
            }
            size = new MPoint(length, row);

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

    public Tile getTile(int x, int y){
        return tiles.get(y * size.getWidth() + x);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void printMap2Console(){
        for(int i = 0; i < map.size(); i++){
            System.out.println(map.get(i));
        }
    }

    public void printTiles2Console(){
        for (int y = 0; y < size.getHeight(); y++) {
            for (int x = 0; x < size.getWidth(); x++)
                System.out.print(tiles.get(y * size.getWidth() + x).getState() > Tile.OBSTACLE ? Tile.FREE_CHAR : Tile.OBST_CHAR);
            System.out.println();
        }
    }
}
