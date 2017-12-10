public class Card {
    private Symbol symbol;
    private int id;

    public Card(int id, Symbol symbol){
        this.id = id;
        this.symbol = symbol;
    }

    public Symbol getCardSymbol(){
        return symbol;
    }
}
