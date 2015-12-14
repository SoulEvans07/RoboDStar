package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.View.Gui;

public class TestGui {
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();
        Gui gui = new Gui();
    }
}
