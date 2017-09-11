package com.krazytar;

public class Player {
    static Room currentRoom;
    static Player currentPlayer;
    String name;
    Race race;
    int lvl;
    int xp;
    
    public Player(String pName, Race pRace) {
        name = pName;
        race = pRace;
    }
    
    public static Room getCurrentRoom() {
        return currentRoom;
    }
    public static void setCurrentRoom(Room pCurrentRoom) {
        currentRoom = pCurrentRoom;
    }
}
