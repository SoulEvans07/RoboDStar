package com.mi.robodstar;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.Model.Robot;
import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.View.Gui;

public class Main {
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();


    }

    public static void exitGame(){
        System.exit(0);
    }
}
