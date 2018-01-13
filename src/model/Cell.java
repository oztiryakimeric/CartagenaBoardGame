package model;

import java.io.Serializable;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Cell implements Serializable {
    private int index;
    private int pirateCount;

    public Cell(int index) {
        this.index = index;
        pirateCount = 0;
    }

    public int getIndex() {
        return index;
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

