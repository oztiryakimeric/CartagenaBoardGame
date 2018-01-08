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

        pirate.getCell().pirateLeft();
        Cell possibleCell = null;

        if(pirate.getCell() instanceof BeginCell)
            possibleCell = segments[0].findForwardCell(BeginCell.getInstance(), symbol, false);
        else if(pirate.getCell() instanceof BoatCell)
            possibleCell = BoatCell.getInstance();
        else if(pirate.getCell() instanceof GameCell)
            possibleCell = ((GameCell) pirate.getCell()).getSegment().findForwardCell(pirate.getCell(), symbol, false);

        if(possibleCell == null)
            possibleCell = segments[(((GameCell) pirate.getCell()).getSegment().getIndex() + 1)].findForwardCell(pirate.getCell(), symbol, true);


        if(possibleCell != null)
            return possibleCell;
        else
            return BoatCell.getInstance();
    }

    public Cell getBackwardPossibleCell(Pirate pirate, int segmentIndex){
        pirate.getCell().pirateLeft();
        Cell possibleCell = null;
        if(pirate.getCell() instanceof GameCell)
            possibleCell = ((GameCell) pirate.getCell()).getSegment().findBackwardCell(pirate.getCell(), segmentIndex);


        if(possibleCell == null)
            possibleCell = getBackwardPossibleCell(pirate, segmentIndex - 1);

        if(possibleCell != null)
            return possibleCell;
        else
            return BeginCell.getInstance();
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
