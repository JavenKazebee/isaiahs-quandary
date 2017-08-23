package com.krazytar;

public class Player {
    static Room currentRoom;
    
    public static Room getCurrentRoom() {
        return currentRoom;
    }
    public static void setCurrentRoom(Room pCurrentRoom) {
        currentRoom = pCurrentRoom;
    }
}
