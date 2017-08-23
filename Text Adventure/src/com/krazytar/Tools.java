package com.krazytar;

import java.util.ArrayList;

public class Tools {
    public static String[] slashSeperate(String input) {
        String[] output = input.split("/");
        return output;
    }
    public static ArrayList<String> toArrayList(String[] input) {
        ArrayList<String> output = new ArrayList();
        for(String str : input) {
            output.add(str);
        }
        return output;
    }
    public static boolean checkExists(Object o) {
        return o != null;
    }
}
