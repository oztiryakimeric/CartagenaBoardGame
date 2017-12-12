package model;

import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class Deck {
    private static Deck instance;
    private Stack<Symbol> deck;

    private Deck(){
        deck = new Stack<>();
        initCards();
    }

    public static Deck getInstance(){
        if(instance == null)
            instance = new Deck();
        return instance;
    }

    private void initCards(){
        List<Symbol> symbols = Symbol.getSymbols();
        Collections.shuffle(symbols);

        for(int i = 0; i < symbols.size(); i++)
            for(int j=0; j<30; j++)
                deck.add(symbols.get(i));
    }

    public Symbol getTopCard(){
        return deck.pop();
    }

    public List<Symbol> getDeck(){
        return deck;
    }
}
