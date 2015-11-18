package com.mi.robodstar.Utility;

import com.mi.robodstar.Defaults.Reference;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GuiMagic {
    public static final Border frameBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public static void setFrameSize(JFrame frame, int width, int height){
        int wp = Reference.WIN_WBORDER;
        int hp = Reference.WIN_HBORDER;
        frame.setSize(width + wp, height + wp);
        frame.setPreferredSize(new Dimension(width + wp, height + hp));
    }

    public static final String WIN_LOOKS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    public static void setWindowTheme(String set){
        try {
            UIManager.setLookAndFeel(set);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
