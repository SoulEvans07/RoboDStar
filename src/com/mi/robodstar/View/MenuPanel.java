package com.mi.robodstar.View;

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
        test2.addActionListener(new test2Action());

        initLayout();
    }

    public void initLayout(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(250,50));
        this.add(test1, FlowLayout.LEFT);

        this.add(test2, FlowLayout.CENTER);


    }


    private class test1Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.test.startTest1();
        }
    }

    private class test2Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.test.startTest2();
        }
    }

}
