package model;

import java.util.Arrays;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private int index;
    private GameCell[] cells;
    private Segment next;

    public Segment(int index, int length) {
        this.index = index;
        cells = new GameCell[length];
    }

    public Cell findBackwardCell(Cell pirateCell, int searchingSegment){
        int index;

        if (pirateCell instanceof BeginCell)
            return BeginCell.getInstance();
        else
            index = pirateCell.getIndex() % cells.length;

        while(index > -1) {
            if (!cells[index].isOccupied() && cells[index].isFewer(pirateCell))
                return cells[index];
            index--;
        }
        if(searchingSegment == 0)
            return BeginCell.getInstance();
        else
            return null;
    }

    public void setNext(Segment next) {
        this.next = next;
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

    @Override
    public String toString() {
        return "{(" + index + ") " + Arrays.toString(cells) + "} -> ";
    }
}
