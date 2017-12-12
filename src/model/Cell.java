package model;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Cell{
    private int index;

    public Cell(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFurther(Cell c){
        return this.index > c.index;
    }
}

