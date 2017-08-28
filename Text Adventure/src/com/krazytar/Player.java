package com.krazytar;

import java.io.File;

public class Player {
    static Room currentRoom;
    String name;
    Race race;
    int warriorLvl, warriorXP, mageLvl, mageXP, thiefLvl, thiefXP;
    
    public Player(String pName, Race pRace) {
        name = pName;
        race = pRace;
        File file = new File("data/" + name + ".xml");
        if(!file.exists()) {
            file.mkdir();
        }
    }
    
    public static Room getCurrentRoom() {
        return currentRoom;
    }
    public static void setCurrentRoom(Room pCurrentRoom) {
        currentRoom = pCurrentRoom;
    }
}
