package com.krazytar;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.jdom2.input.*;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Loader {
    static String currentSave;
    
    public static Room loadRoom(String id) {
        SAXBuilder sb = new SAXBuilder();
        File input = new File("saves/" + currentSave + "/rooms/" + id + ".xml");
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
                    e.getChildText("room"),
                    e.getChildText("name"), 
                    Tools.toArrayList(Tools.slashSeperate(e.getChildText("commands")))));
        }
        }
        
        return new Room(id, name, desc, exits);
        
    }
    
    public static String commandHelp(String command) {
        SAXBuilder sb = new SAXBuilder();
        File input = new File("help/commandHelp.xml");
        Document doc = null;
        try {
            doc = sb.build(input);
        } catch(JDOMException | IOException exc) {
            exc.printStackTrace();
        }
        return doc.getRootElement().getChildText(command);
    }
    
    public static ArrayList<String> loadWorlds() {
        ArrayList<String> worlds = new ArrayList();
        SAXBuilder sb = new SAXBuilder();
        File[] worldFiles = new File("worlds").listFiles();
        for(File f : worldFiles) {
            worlds.add(f.getName());
        }
        return worlds;
    }
    
    public static ArrayList<Race> loadRaces() {
        ArrayList<Race> races = new ArrayList();
        SAXBuilder sb = new SAXBuilder();
        File[] raceFiles = new File("saves/" + currentSave + "/races").listFiles();
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
    
    public static void createWorld(String world, String saveName) {
        Printer.print("Creating new world...");
        File worldFile = new File("worlds/" + world);
        File saveFile = new File("saves/" + saveName);
        try {
            FileUtils.copyDirectory(worldFile, saveFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Printer.print("Done!");
    }
    
    public static SaveLoad loadSave(String saveName) {
        File f = new File("saves/" + saveName);
        if(!f.exists()) {
            Printer.print("World does not exist.");
            return SaveLoad.SAVE_NO_EXIST;
        }
        currentSave = saveName;
        File pFile = new File("saves/" + saveName + "/player.xml");
        if(pFile.exists()) {
            return SaveLoad.NORMAL;
        }
        return SaveLoad.PLAYER_NO_EXIST;
    }
    
    public static ArrayList<String> loadSaves() {
        ArrayList<String> saves = new ArrayList();
        SAXBuilder sb = new SAXBuilder();
        File[] saveFiles = new File("saves").listFiles();
        for(File f : saveFiles) {
            saves.add(f.getName());
        }
        return saves;
    }
    
    public static void createChar(Player p) {
        File f = new File("saves/" + currentSave + "/player.xml");
        Element root = new Element("player");
        Document doc = new Document();
        doc.setRootElement(root);
        Element name = new Element("name");
        Element race = new Element("race");
        doc.getRootElement().addContent(name);
        doc.getRootElement().addContent(race);
        name.setText(p.name);
        race.setText(p.race.name);

        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());
        try {
            f.createNewFile();
            out.output(doc, new FileWriter(f));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String loadMessage(String messageName) {
        File messagesFile = new File("help/messages.xml");
        SAXBuilder sb = new SAXBuilder();
        Document doc = null;
        try {
            doc = sb.build(messagesFile);
        } catch (IOException | JDOMException exc) {
            exc.printStackTrace();
        }
        return doc.getRootElement().getChild(messageName).getText();
    }
    
    public static String loadMessage(String messageName, String[] variables) {
        File messagesFile = new File("help/messages.xml");
        SAXBuilder sb = new SAXBuilder();
        Document doc = null;
        try {
            doc = sb.build(messagesFile);
        } catch (IOException | JDOMException exc) {
            exc.printStackTrace();
        }
        String message = doc.getRootElement().getChild(messageName).getText();
        int counter = 0;
        while (true) {
            if(message.contains("#" + counter)) {
                message = message.replace("#" + counter, variables[counter]);
            } else {
                break;
            }
            counter++;
        }
        return message;
    }
}
