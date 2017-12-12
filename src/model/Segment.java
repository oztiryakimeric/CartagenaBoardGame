package model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private int index;
    private GameCell[] cells;

    public Segment(int index, int length) {
        this.index = index;
        cells = new GameCell[length];
    }

    public GameCell findCell(Symbol symbol){
        for(int i = 0; i< cells.length; i++)
            if(cells[i].getSymbol().equals(symbol))
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

    @Override
    public String toString() {
        return "{(" + index + ") " + Arrays.toString(cells) + "} -> ";
    }
}
