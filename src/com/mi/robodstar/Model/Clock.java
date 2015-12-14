package com.mi.robodstar.Model;

import com.mi.robodstar.Defaults.Reference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock {
    private static int counter;
    private static Timer timer;

    /*public Clock(){
        counter = 0;
        timer = new Timer(Reference.TIMER_TICK, new action());
    }*/

    private static void tick() {
        counter++;
        // TODO: Game.tick();
    }

    public static void set(){
        counter = 0;
        timer = new Timer(Reference.TIMER_TICK, new action());
    }

    public static void pauseClock(){
        timer.stop();
    }

    public static void startClock(){
        timer.start();
    }

    public static void stopClock(){
        timer.stop();
        counter = 0;
    }

    public static int getTime(){
        return counter;
    }

    private static class action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    }

}