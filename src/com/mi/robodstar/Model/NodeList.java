package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeList {
    private ArrayList<Node> nodes;

    public NodeList(){
        nodes = new ArrayList<>();
    }

    public NodeList(ArrayList<Node> nodes){
        this.nodes = nodes;
        sort();
    }

    public void add(Node node){
        nodes.add(node);
        this.sort();
    }

    public Node get(int i){
        return nodes.get(i);
    }

    public Node get(MPoint pos){
        for(Node node : nodes){
            if(pos.equals(node.pos)) {
                return node;
            }
        }
        return null;
    }

    public int indexOf(Node node){
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).pos.equals(node.pos)) {
                return i;
            }
        }
        return -1;
    }

    public void remove(Node node){
        int i = indexOf(node);
        if(i >= 0) {
            nodes.remove(i);
        }
        sort();
    }


    public boolean contains(MPoint pos){
        for(Node node : nodes)
            if(node.pos.equals(pos)) {
                return true;
            }
        return false;
    }

    public boolean contains(Node node){
        return this.contains(node.pos);
    }


    public void sort(){
        Collections.sort(nodes);
    }

    public int size() {
        return nodes.size();
    }

    public List<Node> getList(){
        return nodes;
    }


    public void printList(String tag){
        System.out.println(">>>>>>>>>>>>>>" + tag);
        for(int i = 0; i < nodes.size(); i++)
            System.out.println("[" + i + "] [" + nodes.get(i).pos.getWidth() + ", " + nodes.get(i).pos.getHeight() + "] (" +
                    nodes.get(i).steps + " + " + nodes.get(i).dist + " = " + nodes.get(i).getF() + ")");
        System.out.println("<<<<<<<<<<<<<<" + tag);
    }
}
