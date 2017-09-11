package com.krazytar;

import java.util.ArrayList;

public class Exit {
    String roomID;
    ArrayList<String> commands;
    String name;
    
    public Exit(String pRoomID, String pName, ArrayList<String> pCommands) {
        roomID = pRoomID;
        name = pName;
        commands = pCommands;
    }
    public String getRoomID() {
        return roomID;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getCommands() {
        return commands;
    }
    
}
