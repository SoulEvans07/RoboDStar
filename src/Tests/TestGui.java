package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.Model.Robot;
import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.View.Gui;

public class TestGui {
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();

        TestChamber testChamber= new TestChamber();
        Robot A = new Robot();
        Robot D = new Robot();
        testChamber.addRobot(A);
        testChamber.addRobot(D);

        Gui gui = new Gui(testChamber);
    }
}
