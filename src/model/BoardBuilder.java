package model;

import java.util.Collections;
import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class BoardBuilder {
    public static Board build(int segmentCount, List<Symbol> symbolList){
        Segment[] segments = new Segment[segmentCount];

        for(int i=0; i<segmentCount; i++)
            segments[i] = generateRandomSegment(i, symbolList);

        return new Board(segments);
    }

    private static Segment generateRandomSegment(int index, List<Symbol> symbolList){
        Segment segment = new Segment(index);
        Collections.shuffle(symbolList);
        GameCell[] cells = new GameCell[symbolList.size()];

        for(int i = 0; i < symbolList.size(); i++)
            cells[i] = new GameCell(segment, i, symbolList.get(i));
        segment.setCells(cells);

        return segment;
    }
}
