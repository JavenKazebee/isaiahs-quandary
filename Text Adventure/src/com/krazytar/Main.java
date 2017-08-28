package com.krazytar;

public class Main {
    
    public static void main(String[] args) {
        boolean running = true;
        Player.setCurrentRoom(Loader.loadRoom(0));
        Loader.loadRaces();
        while(running) {
            String in = Input.readInput();
            Input.runCommand(in);
        }
    }
}
