package model;

import java.util.Arrays;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board {
    private BeginCell beginingCell;
    private Segment[] segments;
    private BoatCell boatCell;
    private static Board instance;

    private Board(int segmentCount) {
        segments = new Segment[segmentCount];
        beginingCell = BeginCell.getInstance();
        boatCell = BoatCell.getInstance();
        initSegments();
    }

    public static Board getInstance(int segmentCount){
        if(instance == null)
            instance = new Board(segmentCount);
        return instance;
    }

    private void initSegments(){
        for(int i = 0; i < segments.length; i++)
            segments[i] = SegmentBuilder.getRandomlyGeneratedSegment(i, Symbol.getSymbols());
    }

    public Segment[] getSegments(){
        return segments;
    }

    public Cell getForwardPossibleCell(Pirate pirate, Symbol symbol){
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

    public Cell getBackwardPossibleCell(Pirate pirate){
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

    public BeginCell getBeginingCell() {
        return beginingCell;
    }

    public BoatCell getBoatCell() {
        return boatCell;
    }

    @Override
    public String toString() {
        return "model.Board : " + BeginCell.getInstance().toString() + Arrays.toString(segments) + BoatCell.getInstance();
    }
}
