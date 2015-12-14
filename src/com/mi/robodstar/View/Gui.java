package com.mi.robodstar.View;

import com.mi.robodstar.Main;
import com.mi.robodstar.Model.*;
import com.mi.robodstar.Model.Robot;
import com.mi.robodstar.Utility.GuiMagic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui extends JFrame {
    private GamePanel gamePanel;
    private TestChamber testChamber;
    //private MenuPanel menuPanel;

    public Gui(){
        GuiMagic.setWindowTheme(GuiMagic.WIN_LOOKS);
        GuiMagic.setFrameSize(this, 100, 100);

        testChamber = new TestChamber();
        testChamber.addRobot(new Robot());setVisible(true);
        gamePanel = new GamePanel(this,testChamber);

        initFrame();
        setVisible(true);
    }

    private void initFrame(){
        this.setLayout(new BorderLayout());

        this.setTitle("A* vs D*");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new exitApp());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(gamePanel);
    }

    private class exitApp extends WindowAdapter {
        public void windowClosing(WindowEvent e){
            requestFocus(false); // letiltjuk, hogy ESC-re ne hozza be a pausePanelt, amíg fent van az OptionPane
            int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if(i == 0) {
                Main.exitGame();
            }
            requestFocus(); // visszaadjuk, ha nem léptünk ki
        }
    }
}
