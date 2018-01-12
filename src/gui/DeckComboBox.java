package gui;

import model.Symbol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class DeckComboBox extends JComboBox<DeckComboBox.DeckItem> {
    private List<Symbol> symbolList;
    private CardSelectedListener cardSelectedListener;

    public DeckComboBox(List<Symbol> symbolList) {
        this.symbolList = symbolList;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cardSelectedListener != null && getSelectedItem() != null){
                    cardSelectedListener.onCardSelected(((DeckItem) getSelectedItem()).getSymbol());
                }
            }
        });

        populate();
    }

    public void addCardSelectedListener(CardSelectedListener listener){
        this.cardSelectedListener = listener;
    }

    private void populate(){

        HashMap<Symbol, Integer> cardMap = new HashMap<>();

        for(Symbol symbol: symbolList){
            int count = cardMap.getOrDefault(symbol, 0);
            cardMap.put(symbol, count+1);
        }

        Iterator it = cardMap.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Symbol symbol = (Symbol) pair.getKey();
            int count = (int) pair.getValue();
            this.addItem(new DeckItem(symbol, count));
            it.remove();
        }
    }

    public void update(){
        this.removeAllItems();
        populate();
    }

    public void setDeck(List<Symbol> deck) {
        this.symbolList = deck;
    }

    class DeckItem{
        private Symbol symbol;
        private int count;

        public DeckItem(Symbol symbol, int count) {
            this.symbol = symbol;
            this.count = count;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return symbol.toString() + " (" + count + ")";
        }
    }

    public interface CardSelectedListener{
        void onCardSelected(Symbol symbol);
    }
}
