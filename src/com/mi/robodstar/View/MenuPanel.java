package com.mi.robodstar.View;

import Tests.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel{
    private Gui parent;
    private JButton test1;
    private JButton test2;
    private JButton test3;

    public MenuPanel(Gui p){
        parent = p;
        test1 = new JButton("Test 1");
        test1.addActionListener(new test1Action());

        test2 = new JButton("Test 2");
        test2.addActionListener(new test1Action());

        test3 = new JButton("Test 3");
        test3.addActionListener(new test1Action());

        initLayout();
    }

    public void initLayout(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(250,50));
        this.add(test1, FlowLayout.LEFT);

        this.add(test2, FlowLayout.CENTER);

        this.add(test3, FlowLayout.RIGHT);

    }


    private class test1Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.test.startTest1();
        }
    }

}
