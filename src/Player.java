import java.awt.*;
import java.util.ArrayList;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Player {
    private int id;
    private Color color;
    private Pawn[] pawnArray;
    private ArrayList<Pawn> pawnInBoat;

    public Player(int id, Color color) {
        this.id = id;
        this.color = color;
        createPawns(6);
    }

    private void createPawns(int count){
        pawnArray = new Pawn[count];

        for(int i=0; i<count; i++)
            pawnArray[i] = new Pawn();
    }

    private void moveBoat(Pawn pawn){
        pawnInBoat.add(pawn);
    }

    public void movePawn(Pawn pawn, Symbol symbol){

    }

    public int getInBoat(){
        return pawnInBoat.size();
    }

}
