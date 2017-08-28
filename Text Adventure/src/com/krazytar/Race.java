package com.krazytar;

public class Race {
    int intelligence, agility, strength;
    String name;
    String desc;
    
    public Race(String pName, String pDesc, int pInt, int pAg, int pStr) {
        name = pName;
        desc = pDesc;
        intelligence = pInt;
        agility = pAg;
        strength = pStr;
    }
}
