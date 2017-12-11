/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Cell{
    private int index;

    public Cell(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

class GameCell extends Cell{
    private Segment segment;
    private Symbol symbol;

    //celler segmenti ve o segmentteki indexini tutsun dedim, aramak daha kolay oldu. ayrıca olustururken 6 yla carp vs
    //ondan kurtulduk. pawn a da boyle bisey yapılabilir.
    public GameCell(Segment segment, int index, Symbol symbol) {
        super(index);
        this.segment = segment;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol.toString();
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

    @Override
    public String toString() {
        return "BEGIN CELL";
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
        super(38);
    }

    @Override
    public String toString() {
        return "BOAT";
    }
}