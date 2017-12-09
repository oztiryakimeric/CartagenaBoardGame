import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public enum Symbol {
    BOTTLE, KEYS, SWORD, SKULL, HAT, PISTOL;

    public static List<Symbol> generateRandomSymbolSet(){
        List<Symbol> symbolList = new ArrayList<>();
        symbolList.add(BOTTLE);
        symbolList.add(KEYS);
        symbolList.add(SWORD);
        symbolList.add(SKULL);
        symbolList.add(HAT);
        symbolList.add(PISTOL);
        Collections.shuffle(symbolList);
        return symbolList;
    }
}
