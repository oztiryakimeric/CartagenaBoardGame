package model;

import java.io.Serializable;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board  implements Serializable {
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
            return boatCell;
        }

        for(int j=currentCellIndex + 1; j<segments[currentSegmentIndex].getCells().length; j++){
            GameCell possibleCell = segments[currentSegmentIndex].getCells()[j];

            if(possibleCell.getSymbol().equals(symbol) && possibleCell.getPirateCount() == 0)
                return possibleCell;

        }

        for(int i=currentSegmentIndex + 1; i<segments.length; i++){
            for(int j=0; j<segments[i].getCells().length; j++){
                GameCell possibleCell = segments[i].getCells()[j];

                if(possibleCell.getSymbol().equals(symbol) && possibleCell.getPirateCount() == 0)
                    return possibleCell;

            }
        }

        return BoatCell.getInstance();
    }

    public Cell findPossibleBackwardCell(Pirate pirate){
        int currentSegmentIndex = 0;
        int currentCellIndex = 0;

        if(pirate.getCell() instanceof BeginCell)
            return beginingCell;

        else if(pirate.getCell() instanceof GameCell){
            currentSegmentIndex = ((GameCell) pirate.getCell()).getSegment().getIndex();
            currentCellIndex = pirate.getCell().getIndex();
        }

        else if(pirate.getCell() instanceof BoatCell){
            currentSegmentIndex = segments.length - 1;
            currentCellIndex = segments[0].getCells().length - 1;
        }

        for(int j=currentCellIndex - 1; j>=0; j--){
            GameCell possibleCell = segments[currentSegmentIndex].getCells()[j];

            if(possibleCell.getPirateCount() > 0 && possibleCell.getPirateCount() < 3)
                return possibleCell;
        }

        for(int i=currentSegmentIndex - 1; i>=0; i--){
            for(int j=segments[0].getCells().length-1; j>=0; j--){
                GameCell possibleCell = segments[i].getCells()[j];

                if(possibleCell.getPirateCount() > 0 && possibleCell.getPirateCount() < 3)
                    return possibleCell;
            }
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
