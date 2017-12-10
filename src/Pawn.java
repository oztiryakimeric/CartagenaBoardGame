import java.awt.*;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Pawn {
    private Cell cell;
    private int index;

    public Pawn() {
        index = 0;
        this.cell = BeginCell.newInstance();
    }

    public int getIndex(){
        return index;
    }

    void setIndex(int index){
        this.index = index;
    }
}
