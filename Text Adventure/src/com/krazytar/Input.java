package com.krazytar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class Input {
    public static Status status;
    static String name;
    static Race race;
    
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().toLowerCase();
    }
    
    public static void runCommand(String command) {
        String[] com = command.split(" ");
        
        // The main menu status
        if(status == Status.MAIN) {
            if(com[0].equals("worlds")) {
                Printer.print(Loader.loadMessage("avaliable-worlds"));
                ArrayList<String> worlds = Loader.loadWorlds();
                for(String s : worlds) {
                    Printer.print(" " + s);
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
                        Printer.print(Loader.loadMessage("save-no-exist"));
                    } else if (sl == SaveLoad.PLAYER_NO_EXIST) {
                        Printer.print(Loader.loadMessage("char-no-exist"));
                        status = Status.CREATE_CHAR;
                    } else {
                        Printer.print(Loader.loadMessage("save-loaded", new String[]{com[1]}));
                        status = Status.NORMAL;
                        Player.currentRoom = Loader.loadRoom("start");
                    }
                }
            } else if(com[0].equals("saves")) {
                Printer.print(Loader.loadMessage("avaliable-saves"));
                ArrayList<String> saves = Loader.loadSaves();
                for(String s : saves) {
                    Printer.print(" " + s);
                }
            } else if(com[0].equals("delete")) {
                if(com.length > 1) {
                    if(Loader.loadSaves().contains(com[1])) {
                        try {
                            FileUtils.deleteDirectory(new File("saves/" + com[1]));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
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
                    Printer.print(Loader.loadMessage("no-help-found"));
                }
            }
        }
        
        // The status to create your character
        else if(status == Status.CREATE_CHAR) {
            
            if(com[0].equals("name")) {
                if(com.length > 1) {
                    name = com[1];
                } else {
                    Printer.print("Syntax: name [char name]");
                }
            }
            
            else if(com[0].equals("race")) {
                if(com.length > 1) {
                    ArrayList<Race> races = Loader.loadRaces();
                    for(Race r : races) {
                        if(r.name.equals(com[1])) {
                            race = r;
                            Printer.print(Loader.loadMessage("race-selected", new String[] {race.name}));
                            break;
                        }
                    }
                    
                    if(race == null) Printer.print(Loader.loadMessage("race-no-exist", new String[] {com[1]}));
                }
            }
            
            else if(com[0].equals("races")) {
                for(Race r : Loader.loadRaces()) {
                        Printer.print(r.name + "\n");
                    }
            }
            
            else if(com[0].equals("accept")) {
                if(name != null && race != null) {
                    Loader.createChar(new Player(name, race));
                }
                Printer.print(Loader.loadMessage("char-created"));
            } 
            
            else if(com[0].equals("stats")) {
                Printer.print(Loader.loadMessage("get-char-stats", new String[] {name, race.name}));
            }
        }
    }
}
