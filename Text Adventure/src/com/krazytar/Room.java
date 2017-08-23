package com.krazytar;

import java.util.ArrayList;

public class Room {
    int id;
    String name;
    String desc;
    ArrayList<Exit> exits;
    
    public Room(int pID, String pName, String pDesc, ArrayList<Exit> pExits) {
        id = pID;
        name = pName;
        desc = pDesc;
        exits = pExits;
        System.out.println(desc);
    }
    
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
}
