package com.krazytar;

public class Room {
    int id;
    String name;
    String desc;
    
    public Room(int pID, String pName, String pDesc) {
        id = pID;
        name = pName;
        desc = pDesc;
        System.out.println("A room has been created!\nName: " + name + "\nDescription: " + desc);
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
