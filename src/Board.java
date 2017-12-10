import java.util.ArrayList;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board {
    private Segment[] segmentArray;
    private static Board instance;
    //private ArrayList<Cell> cellList;

    private Board() {
        /*cellList = new ArrayList<>();
        segmentArray = new Segment[6];
        cellList.add(BeginCell.newInstance());
        initSegments();
        cellList.add(BoatCell.newInstance());*/

        initSegments();
        segmentArray = new Segment[6];
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
            //addCellsToList(segmentArray[i]);
        }
        BoatCell.newInstance();
    }

    public Segment[] getSegmentList(){
        return segmentArray;
    }

    /*private void addCellsToList(Segment segment){
        for(int i = 0; i < segment.getCellList().size(); i++){
            cellList.add((Cell) segment.getCellList().get(i));
        }
    }*/
}
