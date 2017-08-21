package com.krazytar;

import java.io.File;
import java.io.IOException;
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
        return new Room(id, name, desc);
        
    }
}
