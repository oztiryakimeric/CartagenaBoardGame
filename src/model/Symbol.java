package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Symbol{
    public static List<Symbol> getSymbols(){
        List<Symbol> symbolList = new ArrayList<>();
        symbolList.add(new Symbol("BOTTLE"));
        symbolList.add(new Symbol("KEYS"));
        symbolList.add(new Symbol("SWORD"));
        symbolList.add(new Symbol("SKULL"));
        symbolList.add(new Symbol("HAT"));
        symbolList.add(new Symbol("PISTOL"));
        return symbolList;
    }

    private String text;

    public Symbol(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        Symbol o = (Symbol) obj;
        return this.text.equals(o.getText());
    }
}
