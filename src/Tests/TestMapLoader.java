package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.Model.MazeMap;

public class TestMapLoader {
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();
        MazeMap test = new MazeMap(Config.getMapPath());

        test.printMap2Console();
        System.out.println("\n");
        test.printTiles2Console();

        //>>>> PASSED <<<<//
    }
}
