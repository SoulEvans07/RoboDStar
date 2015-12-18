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
        parent.setWidth((int)(cols * (lineWidth + tileSize)*1.5) + 2*lineWidth);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawMaze(g2);
        drawAStarHmap(g2);
        drawDStarHmap(g2);
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

    void drawAStarHmap(Graphics2D g) {
        int offset = tileSize + lineWidth;
        Tile temp;
        g.setColor(lineColor);
        g.fillRect(cols*offset+lineWidth, 0, offset/2 * cols + lineWidth/2, offset/2 * rows + lineWidth/2);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                temp = testChamber.getRobots().get(0).getHmap().getTile(i, j);
                g.setColor(temp.getColor());
                g.fillRect(cols*offset+lineWidth+ lineWidth/2 + i * offset/2, lineWidth/2 + j * offset/2, tileSize/2, tileSize/2);
            }
        }
    }

    void drawDStarHmap(Graphics2D g) {
        int offset = tileSize + lineWidth;
        Tile temp;
        g.setColor(lineColor);
        g.fillRect(cols*offset+lineWidth, rows*offset/2+lineWidth/2, offset/2 * cols + lineWidth/2,rows*offset+lineWidth);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                temp = testChamber.getRobots().get(1).getHmap().getTile(i, j);
                g.setColor(temp.getColor());
                g.fillRect(cols*offset+lineWidth+ lineWidth/2 + i * offset/2, rows*offset/2+lineWidth/2 + lineWidth/2 + j * offset/2, tileSize/2, tileSize/2);
            }
        }
    }
}

