package model;

import java.util.Arrays;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board {
    private BeginCell beginingCell;
    private Segment[] segmentArray;
    private BoatCell boatCell;
    private static Board instance;

    private Board(int segmentCount) {
        segmentArray = new Segment[segmentCount];
        beginingCell = BeginCell.newInstance();
        boatCell = BoatCell.newInstance();
        initSegments();
    }

    public static Board getInstance(int segmentCount){
        if(instance == null)
            instance = new Board(segmentCount);
        return instance;
    }

    private void initSegments(){
        for(int i = 0; i < segmentArray.length; i++)
            segmentArray[i] = SegmentBuilder.getRandomlyGeneratedSegment(i, Symbol.getSymbols());
    }

    public Segment[] getSegmentArray(){
        return segmentArray;
    }

    public Cell getPossibleCell(Pawn pawn, Symbol symbol){
        int pawnSegment = pawn.getIndex() / 6;
        Cell cell = segmentArray[pawnSegment].findCell(symbol);
        if(cell != null && cell.getIndex() > pawn.getIndex())
            return cell;
        else
            return BoatCell.newInstance();
    }

    public BeginCell getBeginingCell() {
        return beginingCell;
    }

    public BoatCell getBoatCell() {
        return boatCell;
    }

    @Override
    public String toString() {
        return "model.Board : " + BeginCell.newInstance().toString() + Arrays.toString(segmentArray) + BoatCell.newInstance();
    }
}
