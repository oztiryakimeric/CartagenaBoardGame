import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.List;
import java.util.Random;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private GameCell[] cellArray;


    public Segment(int length) {
        cellArray = new GameCell[length];
        initCells();
    }

    private void initCells(){
        List<Symbol> shufledSymbols = Symbol.generateRandomSymbolSet();

        for(int i=0; i<cellArray.length; i++)
            cellArray[i] = new GameCell(shufledSymbols.get(i));
    }
}
