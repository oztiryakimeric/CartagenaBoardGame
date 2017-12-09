/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public abstract class Cell {
    private int index;

    public Cell(int index) {
        this.index = index;
    }
}

class GameCell{
    private Symbol symbol;

    public GameCell(Symbol symbol) {
        this.symbol = symbol;
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
    public BoatCell() {
        super(37);
    }
}