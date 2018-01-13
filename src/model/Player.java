package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Player implements Serializable {
    private int id;
    private List<Pirate> pirateList;
    private List<Symbol> deck;

    public Player(int id, List<Pirate> pirateList, List<Symbol> symbolList) {
        this.id = id;
        this.pirateList = pirateList;
        this.deck = symbolList;
    }

    public boolean isWinner(){
        int pirateInBoatCount = 0;
        for(Pirate p: pirateList)
            if(p.getCell().equals(BoatCell.getInstance()))
                pirateInBoatCount++;
        return pirateInBoatCount == pirateList.size();
    }

    public Pirate getPirateAt(Cell cell){
        for(Pirate pirate: pirateList)
            if(pirate.getCell().equals(cell))
                return pirate;
        return null;
    }

    public void discard(Symbol symbol){
        for(int i=0; i<deck.size(); i++)
            if(deck.get(i).equals(symbol)){
                deck.remove(i);
                return;
            }
    }

    public boolean hasValidMove(Board board){
        if(deck.size() > 0) return true;

        for(Pirate pirate: pirateList){
            Cell possibleCell = board.findPossibleBackwardCell(pirate);
            if(!possibleCell.equals(pirate.getCell()))
                return true;
        }
        return false;
    }

    public void addCard(Symbol card){
        deck.add(card);
    }

    public List<Symbol> getDeck() {
        return deck;
    }

    public List<Pirate> getPirateList() {
        return pirateList;
    }

    public int getId() {
        return id;
    }

}
