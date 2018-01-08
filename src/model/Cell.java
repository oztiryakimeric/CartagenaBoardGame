package model;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Cell{
    private int index;
    private int piratesOnThisCell;

    public Cell(int index) {
        this.index = index;
        piratesOnThisCell = 0;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFurther(Cell cell){
        return this.index > cell.index;
    }

    public boolean isFewer(Cell cell){
        return this.index < cell.index;
    }

    public boolean isOccupied(){
        return piratesOnThisCell > 2;
    }

    public void pirateCame(){
        piratesOnThisCell++;
    }

    public void pirateLeft(){
        piratesOnThisCell--;
    }

    public int getPiratesOnThisCell(){
        return piratesOnThisCell;
    }
}

