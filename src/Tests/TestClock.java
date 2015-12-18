package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.AStar;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.Model.DStar;
import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.View.Gui;

public class TestClock {
    static Gui gui;
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();

        TestChamber testChamber = new TestChamber();
        AStar A = new AStar(testChamber.getStart(), testChamber.getGoal());
        DStar D = new DStar(testChamber.getStart(), testChamber.getGoal());
        testChamber.addRobot(A);
        testChamber.addRobot(D);

        gui = new Gui(testChamber);
        A.calc();
        Clock.startClock();
    }

    public static void tick(){
        gui.tick();
    }
}
