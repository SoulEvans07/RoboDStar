package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Defaults.Reference;
import com.mi.robodstar.Model.AStar;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.Model.DStar;
import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.View.Gui;

public class Test {
    public static Gui gui;
    private TestChamber testChamber;
    public Test(){

    }

        public void startTest1(){
            Config.setDefaults();
            Clock.stopClock();
            testChamber = new TestChamber();
            AStar A = new AStar(testChamber.getChamber(), Reference.HMAP1_PATH, testChamber.getStart(), testChamber.getGoal());
            DStar D = new DStar(testChamber.getChamber(), Reference.HMAP2_PATH, testChamber.getStart(), testChamber.getGoal());
            testChamber.addRobot(A);
            testChamber.addRobot(D);
            gui.loadGame(testChamber);
            A.calc();
            D.startDStar();
            Clock.startClock();
        }
}
