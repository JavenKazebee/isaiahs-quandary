package com.krazytar;

import java.util.ArrayList;

public class Exit {
    int roomID;
    ArrayList<String> commands;
    String name;
    
    public Exit(int pRoomID, String pName, ArrayList<String> pCommands) {
        roomID = pRoomID;
        name = pName;
        commands = pCommands;
    }
    public int getRoomID() {
        return roomID;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getCommands() {
        return commands;
    }
    
}
