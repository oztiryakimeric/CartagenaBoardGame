package model;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */

//pawn ında segment ve cell tutması kolaylastırıcak baya, boattakileri hesaplaması felan daha sekıl olucak
//
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
