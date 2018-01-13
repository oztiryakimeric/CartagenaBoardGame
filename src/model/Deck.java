package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class Deck implements Serializable {
    private static Deck instance;
    private Stack<Symbol> deck;
    List<Symbol> symbolList;

    private Deck(List<Symbol> symbolList){
        deck = new Stack<>();
        this.symbolList = symbolList;
        initCards();
    }

    public static Deck getInstance(List<Symbol> symbolList){
        if(instance == null)
            instance = new Deck(symbolList);
        return instance;
    }

    private void initCards(){
        List<Symbol> symbols = symbolList;
        List<Symbol> tmp = new ArrayList<>(180);

        for(int i = 0; i < symbols.size(); i++)
            for(int j=0; j<30; j++)
                tmp.add(symbols.get(i));
        Collections.shuffle(tmp);
        for(Symbol symbol: tmp)
            deck.push(symbol);
    }

    public Symbol getTopCard(){
        if(deck.isEmpty())
            initCards();
        return deck.pop();
    }

    public List<Symbol> getDeck(){
        return deck;
    }
}
