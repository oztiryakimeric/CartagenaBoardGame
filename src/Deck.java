import java.util.List;

public class Deck {
    private static Deck instance;
    private List<Card> deck;

    private Deck(){
        initCards();
    }

    public static Deck getInstance(){
        if(instance == null)
            instance = new Deck();
        return instance;
    }

    private void initCards(){
        List<Symbol> shuffledDeckSymbols = Symbol.generateRandomSymbolSet();

        for(int i = 0; i < shuffledDeckSymbols.size(); i++){
            deck.add(new Card(i, shuffledDeckSymbols.get(i)));
        }
    }

    //Deck tamamen boşalırsa biz burada çok fena patlarız
    public Card getTopCard(){
        return deck.remove(deck.size() - 1);
    }

    public List<Card> getDeck(){
        return deck;
    }
}
