package model;

import java.io.Serializable;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment implements Serializable {
    private int index;
    private GameCell[] cells;

    public Segment(int index) {
        this.index = index;
    }

    public GameCell[] getCells() {
        return cells;
    }

    public void setCells(GameCell[] cells) {
        this.cells = cells;
    }

    public int getIndex() {
        return index;
    }
}
