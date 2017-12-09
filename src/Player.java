import java.awt.*;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Player {
    private int id;
    private Color color;
    private Pawn[] pawnArray;

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
}
