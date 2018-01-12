package model;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Cell{
    private int index;
    private int pirateCount;

    public Cell(int index) {
        this.index = index;
        pirateCount = 0;
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
        return pirateCount > 2;
    }

    public void pirateCame(){
        pirateCount++;
    }

    public void pirateLeft(){
        pirateCount--;
    }

    public int getPirateCount(){
        return pirateCount;
    }
}

