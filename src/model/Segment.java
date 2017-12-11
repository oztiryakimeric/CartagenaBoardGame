package model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private int index;
    private GameCell[] cellArray;

    public Segment(int index, int length) {
        this.index = index;
        cellArray = new GameCell[length];
        initCells();
    }

    private void initCells(){
        List<Symbol> shuffledSymbols = Symbol.generateRandomSymbolSet();

        for(int i = 0; i < cellArray.length; i++)
            cellArray[i] = new GameCell(this, i, shuffledSymbols.get(i));

    }

    public GameCell findCell(Symbol symbol){
        for(int i=0; i<cellArray.length; i++)
            if(cellArray[i].getSymbol().equals(symbol))
                return cellArray[i];
        return null;
    }

    public GameCell[] getCellArray() {
        return cellArray;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "{(" + index + ") " + Arrays.toString(cellArray) + "} -> ";
    }
}
