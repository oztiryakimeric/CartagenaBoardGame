package model;

import java.util.List;
import java.util.Stack;


//burda card classını sildim. sadece burda kullanımsız ve bi ozellik eklememisiz sadece sembol tutuyo
//gerekirse ekleriz ilerde, init card ı degistirdim bide

//bide cardı discard etmek demek direk atıyo mu kartı yoksa deck e geri mi koyuyo
//deck i stack yaptım ayrıca
public class Deck {
    private static Deck instance;
    private Stack<Symbol> deck;

    private Deck(){
        deck = new Stack<>();
        initCards();
    }

    public static Deck getInstance(){
        if(instance == null)
            instance = new Deck();
        return instance;
    }

    private void initCards(){
        List<Symbol> symbolSet = Symbol.generateRandomSymbolSet();

        for(int i = 0; i < symbolSet.size(); i++)
            for(int j=0; j<30; j++)
                deck.add(symbolSet.get(i));
    }

    //model.Deck tamamen boşalırsa biz burada çok fena patlarız
    //sormak lazım hocaya kartlat bitince noluyo diye bilemedim bende
    public Symbol getTopCard(){
        return deck.pop();
    }

    public List<Symbol> getDeck(){
        return deck;
    }
}
