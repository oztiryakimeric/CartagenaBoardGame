import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private ArrayList<Cell> cellList;
    private int length;
    private int index;

    public Segment(int index, int length) {
        this.index = index;
        this.length = length;
        cellList = new ArrayList<>();
        initCells();
    }

    private void initCells(){
        List<Symbol> shuffledSymbols = Symbol.generateRandomSymbolSet();

        for(int i = 0; i < length; i++)
            cellList.add(new GameCell(((index * 6) + i + 1),shuffledSymbols.get(i)));
    }

    public List getCellList(){
        return cellList;
    }
}
