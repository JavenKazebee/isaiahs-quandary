package com.krazytar;

import java.util.ArrayList;

public class Room {
    String id;
    String name;
    String desc;
    ArrayList<Exit> exits;
    
    public Room(String pID, String pName, String pDesc, ArrayList<Exit> pExits) {
        id = pID;
        name = pName;
        desc = pDesc;
        exits = pExits;
        Printer.print(desc);
    }
    
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
}
