package model;

public class GameCell extends Cell{
    private Segment segment;
    private Symbol symbol;

    public GameCell(Segment segment, int index, Symbol symbol) {
        super(index);
        this.segment = segment;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Segment getSegment() {
        return segment;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
