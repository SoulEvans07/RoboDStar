package Tests;

import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Model.Clock;
import com.mi.robodstar.View.Gui;

/**
 * Created by Abel on 2015. 12. 14..
 */
public class TestGui {
    public static void main(String args[]){
        Config.setDefaults();
        Clock.set();
        Gui gui = new Gui();
    }


}
