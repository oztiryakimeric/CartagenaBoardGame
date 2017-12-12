package gui;

import model.Board;
import model.Cell;
import model.Segment;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class BoardView extends JPanel {
    private Board board;

    private GameCellBoard gameCellBoard;

    public BoardView(Board board) {
        this.board = board;
        this.setLayout(new BorderLayout());

        gameCellBoard = new GameCellBoard(board.getSegmentArray());
        add(gameCellBoard);
    }



    private static class GameCellBoard extends JPanel{
        private Segment[] segmentArray;

        public GameCellBoard(Segment[] segmentArray) {
            this.segmentArray = segmentArray;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i=0; i<segmentArray.length; i++) {
                drawSegment(g, segmentArray[i]);
            }
        }

        private void drawSegment(Graphics g, Segment segment){
            for(int i = 0; i<segment.getCells().length; i++)
                drawCell(g, segment, segment.getCells()[i]);
        }

        private void drawCell(Graphics g, Segment segment, Cell cell){
            int cellWidth = getWidth() / segmentArray.length;
            int cellHeight = getHeight() / segmentArray.length;

            int xPos = cellWidth * cell.getIndex();
            int yPos = cellHeight * segment.getIndex();

            if(cell.getIndex()%2 == segment.getIndex()%2)
                g.setColor(Color.red);
            else
                g.setColor(Color.blue);
            g.fillRect(xPos,yPos, cellWidth, cellHeight);
        }
    }
}
