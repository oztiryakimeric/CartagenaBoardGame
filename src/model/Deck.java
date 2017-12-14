package model;

import java.util.ArrayList;
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
        List<Symbol> tmp = new ArrayList<>(180);

        for(int i = 0; i < symbols.size(); i++)
            for(int j=0; j<30; j++)
                tmp.add(symbols.get(i));
        Collections.shuffle(tmp);
        for(Symbol symbol: tmp)
            deck.push(symbol);
    }

    public Symbol getTopCard(){
        return deck.pop();
    }

    public List<Symbol> getDeck(){
        return deck;
    }
}
