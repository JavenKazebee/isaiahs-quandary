package com.krazytar;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static Status status;
    
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().toLowerCase();
    }
    
    public static void runCommand(String command) {
        String[] com = command.split(" ");
        
        // The main menu status
        if(status == Status.MAIN) {
            if(com[0].equals("worlds")) {
                Printer.print("Here are a list of available worlds.");
                ArrayList<String> worlds = Loader.loadWorlds();
                for(String s : worlds) {
                    Printer.print("\n" + s);
                }
            } else if(com[0].equals("new")) {
                if(com.length > 2) {
                    Loader.createWorld(com[1], com[2]);
                    Loader.loadSave(com[2]);
                    status = Status.CREATE_CHAR;
                } else {
                    Printer.print(Loader.commandHelp("new"));
                }
            } else if(com[0].equals("load")) {
                if(com.length > 1) {
                    SaveLoad sl = Loader.loadSave(com[1]);
                    if(sl == SaveLoad.SAVE_NO_EXIST) {
                        Printer.print("That save does not exist.");
                    } else if (sl == SaveLoad.PLAYER_NO_EXIST) {
                        Printer.print("A character has not yet been created for this save.");
                        status = Status.CREATE_CHAR;
                    } else {
                        Printer.print("Save loaded!");
                        status = Status.NORMAL;
                    }
                }
            }
        }
        
        // The standard in game status
        else if(status == Status.NORMAL) {
            // Go command
            if(com[0].equals("go")) {
                if(com.length > 1) {
                    String argument = new String();
                    Exit exit = null;
                    for(int i = 1; i < com.length; i++) {
                        argument += com[i] + " ";
                    }
                    argument = argument.trim();
                    Printer.print(argument);
                    for(Exit e : Player.getCurrentRoom().exits) {
                        for(String c : e.commands) {
                            if(c.equals(argument)) {
                                exit = e;
                                break;
                            }
                        }
                    }
                    if(exit != null) {
                        Player.setCurrentRoom(Loader.loadRoom(exit.roomID));
                    }

                } else {
                    Printer.print(Loader.commandHelp("go"));
                }
            } 

            // Exits command
            else if(com[0].equals("exits")) {
                for(Exit e : Player.getCurrentRoom().exits) {
                    Printer.print(e.name + "/");
                }
            }

            // Chelp command

            else if(com[0].equals("chelp")) {
                if(Loader.commandHelp(com[1]) != null) {
                    Printer.print(Loader.commandHelp(com[1]));
                } else {
                    Printer.print("No help for that command could be found.");
                }
            }
        }
        
        // The status to create your character
        else if(status == Status.CREATE_CHAR) {
            String name;
            Race race;
            
            if(com[0].equals("name")) {
                if(com.length > 1) {
                    name = com[1];
                }
            }
            
            else if(com[0].equals("race")) {
                
            }
            
            else if(com[0].equals("races")) {
                
            }
            
            else if(com[0].equals("accept")) {
                
            }
        }
    }
}
