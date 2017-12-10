/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public abstract class Cell {
    int index;

    public Cell(int index) {
        this.index = index;
    }
}

class GameCell extends Cell{
    private Symbol symbol;

    public GameCell(int index, Symbol symbol) {
        super(index);
        this.symbol = symbol;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public int getIndex(){
        return index;
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
        super(0);
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
        super(37);
    }
}