package model;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private int index;
    private GameCell[] cells;

    public Segment(int index) {
        this.index = index;
    }

    public GameCell findPossibleForwardCell(int startingPoint, Symbol symbol){
        for(int i=startingPoint; i<cells.length; i++)
            if(cells[i].getSymbol().equals(symbol) && cells[i].getPirateCount() == 0)
                return cells[i];
        return null;
    }

    public GameCell findPossibleBackwardCell(int startingPoint){
        for(int i=startingPoint - 1; i>=0; i--)
            if(cells[i].getPirateCount() > 0 && cells[i].getPirateCount() < 3)
                return cells[i];
        return null;
    }

    public GameCell[] getCells() {
        return cells;
    }

    public void setCells(GameCell[] cells) {
        this.cells = cells;
    }

    public int getIndex() {
        return index;
    }
}
