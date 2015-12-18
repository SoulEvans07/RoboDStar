package com.mi.robodstar.Model;

import com.mi.robodstar.Components.MPoint;
import com.mi.robodstar.Defaults.Config;
import com.mi.robodstar.Utility.LogHelper;

import java.util.ArrayList;

public class DStar extends Robot {
    MazeMap radar;  // field of view

    private int at = 0;

    private int startID;
    private int goalID;
    private ArrayList<DNode> nodes;
    private ArrayList<Integer> pathStorage;
    public ArrayList<Integer> insertedList;
    private ArrayList<Integer> reCalculatedNode;
    private ArrayList<Integer> openList;
    private ArrayList<Integer> openNodes;
    private ArrayList<Integer> closedNodes;
    private ArrayList<Integer> updatedNodes;    // List_Of_Updated

    private int width;
    private int height;
    private int infiniteValue;
    private int nodeCount;

    public DStar(MazeMap map, String heuritic, MPoint start, MPoint goal) {
        super(map, heuritic, start, goal, Tile.ROBO2);
        radar = new MazeMap(Config.getFOVSize(), Config.getFOVSize());   // field of view: 5 wide 5 high

        width = map.getSize().getWidth();
        height = map.getSize().getHeight();
        infiniteValue = width * height + 1;
        nodeCount = width * height;

        pathStorage = new ArrayList<>();
        insertedList = new ArrayList<>();
        reCalculatedNode = new ArrayList<>();
        openList = new ArrayList<>();
        openNodes = new ArrayList<>();
        closedNodes = new ArrayList<>();
        updatedNodes = new ArrayList<>();

        nodes = new ArrayList<>();
        for (int y = 0; y < map.getSize().getHeight(); y++) {
            for (int x = 0; x < map.getSize().getWidth(); x++) {
                DNode temp = new DNode();
                temp.posx = x;
                temp.posy = y;
                temp.ID = y * width + x;

                if (pos.equals(new MPoint(x, y)))
                    startID = temp.ID;
                if (goal.equals(new MPoint(x, y)))
                    goalID = temp.ID;

                temp.gScore = 0;
                temp.obstacle = !hMap.isFree(new MPoint(x, y));
                temp.pathNode = false;
                temp.tag = DNode.NEW;
                temp.backPointer = -1;
                nodes.add(temp);
                //LogHelper.line(x + " " + y + " id: " + temp.ID);
            }
        }
    }

    public void startDStar() {
        pathStorage.clear();

        nodes.get(goalID).hValue = 0;
        INSERT(goalID, nodes.get(goalID).hValue);

        double Process_Result = 0;
        while (Process_Result > -1) //&& Node[Start_ID].Tag != "CLOSED")
        {
            Process_Result = PROCESS_STATE();
        }
        RECONSTRUCTION(startID);
    }

    public void updateDStar() {
        closedNodes.clear();
        pathStorage.clear();
        reCalculatedNode.clear();
        double Process_Result = 0;
        while (Process_Result > -1)// && Node[Start_ID].Tag != "CLOSED")
        {
            Process_Result = PROCESS_STATE();
        }
        RECONSTRUCTION(startID);
    }


    private double PROCESS_STATE() {
        double K_Old = -1;
        int X = MIN_STATE();
        if (X == -1) {
            return -1;
        }
        double h_X = nodes.get(X).hValue;
        K_Old = GET_KMIN();
        DELETE(X);
        if (K_Old < h_X) {
            for (int Y : NEIGHBOR(X)) {
                double h_Y = nodes.get(Y).hValue;
                int B_Y = nodes.get(Y).backPointer;
                int B_X = nodes.get(X).backPointer;
                int Tag_Y = nodes.get(Y).tag;
                if (h_Y <= K_Old && h_X > h_Y + ARC_COST(Y, X, B_Y)) {
                    //if the distance from Y to the current node (X) < the current node distance, then X parrent should be Y
                    nodes.get(X).backPointer = Y;
                    h_X = h_Y + ARC_COST(Y, X, B_Y);//must do this line, because h_X need to be updated to reuse in the "else" condition
                    nodes.get(X).hValue = h_X;
                }
            }
        }
        if (K_Old == nodes.get(X).hValue) {
            for (int Y : NEIGHBOR(X)) {
                double h_Y = nodes.get(Y).hValue;
                int B_Y = nodes.get(Y).backPointer;
                int B_X = nodes.get(X).backPointer;
                int Tag_Y = nodes.get(Y).tag;
                if ((Tag_Y == DNode.NEW) || (B_Y == X && h_Y != h_X + ARC_COST(X, Y, B_X)) || (B_Y != X && h_Y > h_X + ARC_COST(X, Y, B_X)))// original:(B_Y != X && h_Y > h_X + ARC_COST(X, Y))
                {
                    double new_h_x;
                    nodes.get(Y).backPointer = X;
                    new_h_x = h_X + ARC_COST(X, Y, B_X);
                    INSERT(Y, new_h_x);
                }
            }
        } else { //This condition is used to update info when a obs appears on the path
            for (int Y : NEIGHBOR(X)) {
                double h_Y = nodes.get(Y).hValue;
                int B_Y = nodes.get(Y).backPointer;
                int B_X = nodes.get(X).backPointer;
                int Tag_Y = nodes.get(Y).tag;
                if ((Tag_Y == DNode.NEW) || (B_Y == X && h_Y != h_X + ARC_COST(X, Y, B_X))) {
                    double new_h_y;
                    nodes.get(Y).backPointer = X;
                    new_h_y = h_X + ARC_COST(X, Y, B_X);
                    INSERT(Y, new_h_y);
                } else {
                    if (B_Y != X && h_Y > h_X + ARC_COST(X, Y, B_X)) {
                        INSERT(X, h_X);
                    } else {
                        if (B_Y != X && h_X > h_Y + ARC_COST(Y, X, B_Y) && Tag_Y == DNode.CLOSED && h_Y > K_Old) {
                            INSERT(Y, h_Y);
                        }
                    }
                }
            }
        }
        return GET_KMIN();
    }

    private ArrayList<Integer> NEIGHBOR(int Current_Node_ID) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        //neighbours.add(-1);
        if (Current_Node_ID - (width) >= 0) {
            if (hMap.isFree(new MPoint(nodes.get(Current_Node_ID - width).posx, nodes.get(Current_Node_ID - width).posy))) {
                int UpperNode_ID;
                UpperNode_ID = Current_Node_ID - (width);
                neighbours.add(UpperNode_ID);
            }
        }
        if (Current_Node_ID + (width) < nodes.size()) {
            if (hMap.isFree(new MPoint(nodes.get(Current_Node_ID + width).posx, nodes.get(Current_Node_ID + width).posy))) {
                int LowerNode_ID;
                LowerNode_ID = Current_Node_ID + (width);
                neighbours.add(LowerNode_ID);
            }
        }
        if (nodes.get(Current_Node_ID).posx + 1 < (width)) {
            if (hMap.isFree(new MPoint(nodes.get(Current_Node_ID).posx + 1, nodes.get(Current_Node_ID).posy))) {
                int RightNode_ID;
                RightNode_ID = Current_Node_ID + 1;
                neighbours.add(RightNode_ID);
            }
        }
        if (nodes.get(Current_Node_ID).posx - 1 >= 0) {
            if (hMap.isFree(new MPoint(nodes.get(Current_Node_ID).posx - 1, nodes.get(Current_Node_ID).posy))) {
                int LeftNode_ID;
                LeftNode_ID = Current_Node_ID - 1;
                neighbours.add(LeftNode_ID);
            }
        }
        return neighbours;
    }

    public void updateArcCost(int nodeID) {
        for (int i = 0; i < pathStorage.size(); i++) {
            if (nodeID == pathStorage.get(i)) {
                for(int k = 0; k < i; k++)
                    if(pos.getHeight() * width + pos.getWidth() == pathStorage.get(k))
                        startID = pathStorage.get(k+1);
                pathStorage.remove(i);
                i--;
                at = 0;
            }
        }
        int cVal = infiniteValue;
        MODIFY_COST(nodeID, cVal);
        nodes.get(nodeID).obstacle = true;
    }


    private double MODIFY_COST(int X, int cVal) {
        if (nodes.get(X).tag == DNode.CLOSED) {
            //X is changed from a normal Node to an Obstacle node
            int hValue = cVal;
            INSERT(X, hValue);
        }
        return GET_KMIN();
    }

    private int MIN_STATE() {
        if (openList.size() > 0) {
            return openList.get(0);
        } else
            return -1;
    }

    private double GET_KMIN() {
        if (openList.size() > 0) {
            int openedNode = openList.get(0);
            return nodes.get(openedNode).key;
        } else
            return -1;
    }

    private void INSERT(int nodeID, double hNew) {
        double kx = -1;
        insertedList.add(nodeID);
        if (nodes.get(nodeID).tag == DNode.NEW) {
            nodes.get(nodeID).tag = DNode.OPEN;
            kx = hNew;
            nodes.get(nodeID).key = kx;
        }
        if (nodes.get(nodeID).tag == DNode.OPEN) {
            kx = Math.min(nodes.get(nodeID).key, hNew);
            nodes.get(nodeID).key = kx;
        }
        if (nodes.get(nodeID).tag == DNode.CLOSED) {
            kx = Math.min(nodes.get(nodeID).hValue, hNew);
            nodes.get(nodeID).key = kx;
            nodes.get(nodeID).hValue = hNew;
            nodes.get(nodeID).tag = DNode.OPEN;
            if (nodes.get(nodeID).obstacle == false) {
                reCalculatedNode.add(nodeID);
            }
        }
        nodes.get(nodeID).hValue = hNew;
        if (openList.size() > 0) {
            int checksum = 0;
            for (int i = 0; i < openList.size(); i++) {
                int openNode = openList.get(i);
                if (nodes.get(nodeID).key <= nodes.get(openNode).key) {
                    openList.add(i, nodeID);
                    openNodes.add(nodeID);
                    nodes.get(nodeID).tag = DNode.OPEN;
                    break;
                } else
                    checksum = checksum + 1;
            }
            //Add the greatest value to the end of array
            if (checksum == openList.size()) {
                openList.add(nodeID);
            }
        } else {
            openList.add(nodeID);
        }
    }

    private void DELETE(int nodeID) {
        openList.remove((Integer) nodeID);
        nodes.get(nodeID).tag = DNode.CLOSED;
        if (nodes.get(nodeID).obstacle == false) {
            closedNodes.add(nodeID);
        }
    }

    private void RECONSTRUCTION(int X) {
        int temp = X;
        pathStorage.clear();
        while (temp != goalID) {
            if (temp > -1) {
                pathStorage.add(temp);
                nodes.get(temp).pathNode = true;
                temp = nodes.get(temp).backPointer;
            }
        }
        pathStorage.add(temp);
    }

    private double ARC_COST(int X, int Y, int Z) {
        if (nodes.get(Y).obstacle == false) {
            if (Z > 0) {
                if (((nodes.get(X).posx == nodes.get(Y).posx) && (nodes.get(Y).posx == nodes.get(Z).posx)) ||
                        ((nodes.get(X).posy == nodes.get(Y).posy) && (nodes.get(Z).posy == nodes.get(X).posy)))
                    return 1;
                else
                    return 1.5;
            } else
                return 1;
        } else {
            return infiniteValue;
        }
    }


    public MazeMap getRadarView() {
        return radar;
    }


    public void refreshView() {
        radar = getRadar();
        int fov = Config.getFOV();
        MPoint temp;
        int hState;
        int rState;
        for (int y = -fov; y <= fov; y++)
            for (int x = -fov; x <= fov; x++) {
                temp = new MPoint(pos.getWidth() + x, pos.getHeight() + y);
                if (!hMap.isOut(temp)) {
                    hState = hMap.getTile(temp.getWidth(), temp.getHeight()).getState();
                    rState = radar.getTile(x + fov, y + fov).getState();
                    if (hState != rState) {
                        if (rState == Tile.OBSTACLE) {
                            updateArcCost(temp.getHeight() * width + temp.getWidth());
                        }
                        hMap.getTile(temp.getWidth(), temp.getHeight()).setState(rState);
                    }
                }
            }
        //hMap.printTiles2Console();
    }

    @Override
    public void tick() {
        if (at < pathStorage.size()) {
            refreshView();
            updateDStar();
            DNode next = nodes.get(pathStorage.get(at));
            MPoint prev = new MPoint(pos);
            pos = new MPoint(next.posx, next.posy);
            at++;
        }
    }


    public void printPathPlan(){
        LogHelper.error("RECONSTRUCTION");
        for (int i = 0; i < pathStorage.size(); i++) {
            new MPoint(nodes.get(pathStorage.get(i)).posx, nodes.get(pathStorage.get(i)).posy).printPos((i == at) ? "----here!!\n" : "\n");
        }
    }

    @Override
    public boolean haveRadar() {
        return true;
    }
}
