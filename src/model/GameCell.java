package model;

public class GameCell extends Cell{
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
