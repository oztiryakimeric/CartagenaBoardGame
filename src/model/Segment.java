package model;

import java.util.Arrays;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Segment {
    private int index;
    private GameCell[] cells;

    public Segment(int index, int length) {
        this.index = index;
        cells = new GameCell[length];
    }

    public Cell findForwardCell(Cell pirateCell, Symbol symbol, boolean successorSegment){
        int index;
        if(successorSegment)
            index = 0;
        else {
            if (pirateCell instanceof BeginCell)
                index = 0;
            else
                index = pirateCell.getIndex() % cells.length;
        }

        while(index < cells.length) {
            if (cells[index].getSymbol().equals(symbol) && cells[index].isFurther(pirateCell))
                return cells[index];
            index++;
        }
        return null;
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
