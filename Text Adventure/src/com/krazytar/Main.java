package com.krazytar;

public class Main {
    
    public static void main(String[] args) {
        boolean running = true;
        Input.status = Status.MAIN;
        while(running) {
            String in = Input.readInput();
            Input.runCommand(in);
        }
    }
}
