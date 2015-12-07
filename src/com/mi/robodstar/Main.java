package com.mi.robodstar;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;

public class Main {
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();
    }
}
