import java.awt.*;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Pawn {
    private int index;

    public Pawn() {
        index = 0;
    }

    public void move(Cell cell){
        index = cell.getIndex();
    }

    public int getIndex(){
        return index;
    }

    void setIndex(int index){
        this.index = index;
    }
}
