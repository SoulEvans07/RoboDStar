package com.mi.robodstar.View;

import com.mi.robodstar.Model.TestChamber;
import com.mi.robodstar.Model.Tile;

import java.awt.*;

public class MyCanvas extends java.awt.Canvas {
    private GamePanel parent;
    TestChamber testChamber;
    private int cols, rows;
    private int lineWidth;
    private int tileSize;
    private Color lineColor;

    public MyCanvas(GamePanel parent, TestChamber testChamber) {
        super();
        this.parent = parent;
        this.testChamber = testChamber;
        cols = testChamber.getSize().getWidth();
        rows = testChamber.getSize().getHeight();
        lineWidth = 2;
        tileSize = 16;
        lineColor = new Color(100, 100, 100);
        parent.setHeight(rows * (lineWidth + tileSize) + lineWidth);
        parent.setWidth(cols * (lineWidth + tileSize) + lineWidth);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawMaze(g2);
        parent.invalidate();
    }

    void drawMaze(Graphics2D g) {
        int offset = tileSize + lineWidth;
        Tile temp;
        g.setColor(lineColor);
        g.fillRect(0, 0, offset * cols + lineWidth, offset * rows + lineWidth);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                temp = testChamber.getChamber().getTile(i, j);
                g.setColor(temp.getColor());
                g.fillRect(lineWidth + i * offset, lineWidth + j * offset, tileSize, tileSize);
            }
        }
    }

}

