/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public abstract class Cell {
    private int index;
    private Symbol symbol;

    public Cell(int index, Symbol symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public int getIndex(){
        return index;
    }
}

class GameCell extends Cell{

    public GameCell(int index, Symbol symbol) {
        super(index, symbol);
    }
}

class BeginCell extends Cell{
    private static BeginCell instance;

    public static BeginCell newInstance(){
        if(instance == null)
            instance = new BeginCell();
        return instance;
    }

    private BeginCell() {
        super(0, Symbol.BEGIN);
    }
}

class BoatCell extends Cell{
    private static BoatCell instance;

    public static BoatCell newInstance(){
        if(instance == null)
            instance = new BoatCell();
        return instance;
    }

    private BoatCell() {
        super(37, Symbol.BOAT);
    }
}