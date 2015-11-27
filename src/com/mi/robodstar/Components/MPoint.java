package com.mi.robodstar.Components;

public class MPoint {
    private int width;
    private int height;

    public MPoint() {
        width = 0;
        height = 0;
    }

    public MPoint(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void set(int width, int height){
        this.width = width;
        this.height = height;
    }
}
