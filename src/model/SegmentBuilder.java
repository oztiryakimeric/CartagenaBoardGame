package model;

import java.util.Collections;
import java.util.List;

/**
 * Created by oztiryakimeric on 13.12.2017.
 */
public class SegmentBuilder {

    public static Segment getRandomlyGeneratedSegment(int index, List<Symbol> symbols){
        Segment segment = new Segment(index, symbols.size());

        Collections.shuffle(symbols);

        GameCell[] cells = new GameCell[symbols.size()];
        for(int i = 0; i < symbols.size(); i++)
            cells[i] = new GameCell(segment, (index * symbols.size()) + i, symbols.get(i));

        segment.setCells(cells);

        return segment;
    }
}
