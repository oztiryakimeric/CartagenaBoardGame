package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public enum Symbol {
    BOTTLE, KEYS, SWORD, SKULL, HAT, PISTOL;

    public static List<Symbol> getSymbols(){
        List<Symbol> symbolList = new ArrayList<>();
        symbolList.add(BOTTLE);
        symbolList.add(KEYS);
        symbolList.add(SWORD);
        symbolList.add(SKULL);
        symbolList.add(HAT);
        symbolList.add(PISTOL);
        return symbolList;
    }

    public String toString(){
        switch (this){
            case BOTTLE:
                return "BOTTLE";
            case KEYS:
                return "KEYS";
            case SWORD:
                return "SWORD";
            case SKULL:
                return "SKULL";
            case HAT:
                return "HAT";
            case PISTOL:
                return "PISTOL";
            default:
                return "UNRECOGNIZED";
        }
    }
}
