import java.util.ArrayList;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Board {
    private Segment[] segmentArray;
    private ArrayList<Cell> cellList;

    public Board() {
        cellList = new ArrayList<>();
        segmentArray = new Segment[6];
        cellList.add(BeginCell.newInstance());
        initSegments();
        cellList.add(BoatCell.newInstance());
    }

    private void initSegments(){
        for(int i = 0; i < segmentArray.length; i++){
            segmentArray[i] = new Segment(i, segmentArray.length);
            addCellsToList(segmentArray[i]);
        }
    }

    private void addCellsToList(Segment segment){
        for(int i = 0; i < segment.getCellList().size(); i++){
            cellList.add((Cell) segment.getCellList().get(i));
        }
    }
}
