import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private GameCell[] cellArray;
    private int index;

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

    @Override
    public String toString() {
        return "{(" + index + ") " + Arrays.toString(cellArray) + "} -> ";
    }
}
