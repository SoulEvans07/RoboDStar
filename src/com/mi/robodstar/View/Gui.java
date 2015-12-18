package com.mi.robodstar.View;

import Tests.Test;
import com.mi.robodstar.Main;
import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.Utility.GuiMagic;
import com.mi.robodstar.Utility.LogHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui extends JFrame {
    private GamePanel gamePanel;
    private TestChamber testChamber;
    private MenuPanel menuPanel;
    public Test test;

    public Gui(){
        menuPanel = new MenuPanel(this);
        initFrame();
        setVisible(true);
    }

    public void setTest(Test test){
        this.test=test;
    }

    public void loadGame(TestChamber testChamber){
        if (gamePanel != null)
            this.remove(gamePanel);
        if (menuPanel != null)
            this.remove(menuPanel);

        menuPanel = new MenuPanel(this);
        this.add(menuPanel, BorderLayout.NORTH);
        this.testChamber = testChamber;
        gamePanel = new GamePanel(this,testChamber);
        this.add(gamePanel);
        GuiMagic.setFrameSize(this, gamePanel.getWidth(), menuPanel.getPreferredSize().height+gamePanel.getHeight()+this.getInsets().top+5);
        gamePanel.invalidate();
        setVisible(true);
    }

    private void initFrame(){
        GuiMagic.setWindowTheme(GuiMagic.WIN_LOOKS);
        this.setLayout(new BorderLayout());
        this.setTitle("A* vs D*");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new exitApp());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(menuPanel, BorderLayout.NORTH);
        GuiMagic.setFrameSize(this, menuPanel.getPreferredSize().width, menuPanel.getPreferredSize().height+this.getInsets().top+5);
        invalidate();
    }

    public void tick(){
        testChamber.tick();
        gamePanel.tick();
    }

    private class exitApp extends WindowAdapter {
        public void windowClosing(WindowEvent e){
            requestFocus(false); // letiltjuk, hogy ESC-re ne hozza be a pausePanelt, amíg fent van az OptionPane
            // TODO: LATER ENABLE IT!!!
            /*int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
             if(i == 0) {*/
            if(true){
                Main.exitGame();
            }
            requestFocus(); // visszaadjuk, ha nem léptünk ki
        }
    }
}
