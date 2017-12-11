package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public enum Symbol {
    BOTTLE, KEYS, SWORD, SKULL, HAT, PISTOL;

    public static List<Symbol> generateRandomSymbolSet(){
        List<Symbol> symbolList = new ArrayList<>();
        symbolList.add(BOTTLE);
        symbolList.add(KEYS);
        symbolList.add(SWORD);
        symbolList.add(SKULL);
        symbolList.add(HAT);
        symbolList.add(PISTOL);
        Collections.shuffle(symbolList);
        return symbolList;
    }

    public static List<Symbol> generateDeckSymbolSet(){
        List<Symbol> deckSymbols = new ArrayList<>();

        for(int i = 0; i < 30; i++){
            deckSymbols.add(BOTTLE);
            deckSymbols.add(KEYS);
            deckSymbols.add(SWORD);
            deckSymbols.add(SKULL);
            deckSymbols.add(HAT);
            deckSymbols.add(PISTOL);
        }

        Collections.shuffle(deckSymbols);

        return deckSymbols;
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
