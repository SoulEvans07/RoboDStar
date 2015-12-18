package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.*;
import com.mi.robodstar.View.Gui;

public class TestGui {
    static Gui gui;
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();
        //Clock.startClock();

        TestChamber testChamber = new TestChamber();
        AStar A = new AStar(testChamber.getChamber(), testChamber.getStart(), testChamber.getGoal());
        //Robot D = new DStar(testChamber.getStart(), testChamber.getGoal());
        testChamber.addRobot(A);
        //testChamber.addRobot(D);

        gui = new Gui(testChamber);
        //A.algorithm();
        Path path = A.algorithmV2();
        //path.printPath();

        for(int y = 0; y < testChamber.getSize().getHeight(); y++)
            for(int x = 0; x < testChamber.getSize().getWidth(); x++){
                if(path.getSolution().get(y * testChamber.getSize().getWidth() + x))
                    testChamber.getChamber().getTile(x, y).setState(Tile.JOINT_TRACE);
            }
        tick();
    }

    public static void tick(){
        gui.tick();
    }
}
