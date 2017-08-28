package com.krazytar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.input.*;
import org.jdom2.*;

public class Loader {
    
    public static Room loadRoom(int id) {
        SAXBuilder sb = new SAXBuilder();
        File input = new File("data/rooms/" + id + ".xml");
        Document doc = null;
        try {
            doc = sb.build(input);
        } catch(JDOMException | IOException exc) {
            exc.printStackTrace();
        }
        Element root = doc.getRootElement();
        String name = root.getChildText("name");
        String desc = root.getChildText("desc");
        ArrayList<Exit> exits = new ArrayList();
        if(Tools.checkExists(root.getChild("exits"))) {
            for(Element e : root.getChild("exits").getChildren("exit")) {
            exits.add(new Exit(
                    Integer.parseInt(e.getChildText("room")), 
                    e.getChildText("name"), 
                    Tools.toArrayList(Tools.slashSeperate(e.getChildText("commands")))));
        }
        }
        
        return new Room(id, name, desc, exits);
        
    }
    
    public static String commandHelp(String command) {
        SAXBuilder sb = new SAXBuilder();
        File input = new File("data/help/commandHelp.xml");
        Document doc = null;
        try {
            doc = sb.build(input);
        } catch(JDOMException | IOException exc) {
            exc.printStackTrace();
        }
        return doc.getRootElement().getChildText(command);
    }
    
    public static ArrayList<Race> loadRaces() {
        ArrayList<Race> races = new ArrayList();
        SAXBuilder sb = new SAXBuilder();
        File[] raceFiles = new File("data/races").listFiles();
        for(File f : raceFiles) {
            Document doc = null;
            try {
                doc = sb.build(f);
            } catch (JDOMException | IOException exc) {
                exc.printStackTrace();
            }
            races.add(new Race(
                    doc.getRootElement().getChildText("name"),
                    doc.getRootElement().getChildText("desc"),
                    Integer.parseInt(doc.getRootElement().getChildText("int")),
                    Integer.parseInt(doc.getRootElement().getChildText("ag")),
                    Integer.parseInt(doc.getRootElement().getChildText("str"))
            ));
        }
        return races;
    }
}
