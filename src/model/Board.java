package model;

import java.util.Arrays;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board {
    private BeginCell beginingCell;
    private Segment[] segments;
    private BoatCell boatCell;

    public Board(Segment[] segments) {
        this.segments = segments;
        beginingCell = BeginCell.getInstance();
        boatCell = BoatCell.getInstance();
    }

    public Cell findPossibleForwardCell(Pirate pirate, Symbol symbol){
        int currentSegmentIndex = 0;
        int currentCellIndex = 0;

        if(pirate.getCell() instanceof BeginCell){
            currentSegmentIndex = 0;
            currentCellIndex = -1;
        }

        else if(pirate.getCell() instanceof GameCell){
            currentSegmentIndex = ((GameCell) pirate.getCell()).getSegment().getIndex();
            currentCellIndex = pirate.getCell().getIndex();
        }

        else if(pirate.getCell() instanceof BoatCell){
            return BoatCell.getInstance();
        }

        GameCell possibleCell = segments[0].findPossibleForwardCell(currentCellIndex + 1, symbol);
        if(possibleCell != null)
            return possibleCell;

        for(int i=currentSegmentIndex + 1; i<segments.length; i++){
            possibleCell = segments[i].findPossibleForwardCell(0, symbol);
            if(possibleCell != null)
                return possibleCell;
        }

        return BoatCell.getInstance();
    }

    public Cell findPossibleBackwardCell(Pirate pirate){
        int currentSegmentIndex = 0;
        int currentCellIndex = 0;

        if(pirate.getCell() instanceof BeginCell)
            return BeginCell.getInstance();

        else if(pirate.getCell() instanceof GameCell){
            currentSegmentIndex = ((GameCell) pirate.getCell()).getSegment().getIndex();
            currentCellIndex = pirate.getCell().getIndex();
        }

        else if(pirate.getCell() instanceof BoatCell){
            currentSegmentIndex = segments.length - 1;
            currentCellIndex = segments[0].getCells().length - 1;
        }

        GameCell possibleCell = segments[0].findPossibleBackwardCell(currentCellIndex - 1);
        if(possibleCell != null)
            return possibleCell;

        for(int i=currentSegmentIndex - 1; i>=0; i--) {
            possibleCell = segments[i].findPossibleBackwardCell(0);
            if (possibleCell != null)
                return possibleCell;
        }

        return pirate.getCell();
    }

    public Segment[] getSegments(){
        return segments;
    }

    public BeginCell getBeginingCell() {
        return beginingCell;
    }

    public BoatCell getBoatCell() {
        return boatCell;
    }
}
