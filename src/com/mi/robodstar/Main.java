package com.mi.robodstar;

import Tests.Test;
import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.Model.Robot;
import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.View.Gui;

public class Main {
    private static  Gui gui;
    public static void main(String args[]){
        Clock.set();
        gui = new Gui();
        Test test = new Test();
        Test.gui=gui;
        gui.setTest(test);

    }
    public static void tick(){
        gui.tick();
    }

    public static void exitGame(){
        System.exit(0);
    }
}
