package com.krazytar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
        File file = new File("saves/" + Loader.currentSave + "/" + name + ".xml");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Element root = new Element("character");
        root.addContent(new Element("name").setText(name));
        root.addContent(new Element("race").setText(race.name));
        Document doc = new Document(root);
        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());
        try {
            out.output(doc, new FileWriter(file));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Room getCurrentRoom() {
        return currentRoom;
    }
    public static void setCurrentRoom(Room pCurrentRoom) {
        currentRoom = pCurrentRoom;
    }
}
