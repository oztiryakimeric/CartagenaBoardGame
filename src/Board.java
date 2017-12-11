import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board {
    private Segment[] segmentArray;
    private static Board instance;

    private Board() {
        segmentArray = new Segment[6];
        initSegments();
    }

    public static Board getInstance(){
        if(instance == null)
            instance = new Board();
        return instance;
    }

    private void initSegments(){
        BeginCell.newInstance();
        for(int i = 0; i < segmentArray.length; i++){
            segmentArray[i] = new Segment(i, segmentArray.length);
        }
        BoatCell.newInstance();
    }

    public Segment[] getSegmentList(){
        return segmentArray;
    }

    public Cell getPossibleCell(Pawn pawn, Symbol symbol){
        int pawnSegment = pawn.getIndex() / 6;
        //burda segmente bu semboldeki cell ini ver diyoruz.
        //eger gelen cell null degılse ve pıyonun onundeyse return edıyoruz. yoksa pıyounun bota gıtmesı gerekır
        //bota gıtmesını soyluyoruz
        Cell cell = segmentArray[pawnSegment].findCell(symbol);
        if(cell != null && cell.getIndex() > pawn.getIndex())
            return cell;
        else
            return BoatCell.newInstance();
    }

    @Override
    public String toString() {
        return "Board : " + BeginCell.newInstance().toString() + Arrays.toString(segmentArray) + BoatCell.newInstance();
    }
}
