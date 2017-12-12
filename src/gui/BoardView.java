package gui;

import model.Board;
import model.Cell;
import model.GameCell;
import model.Segment;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class BoardView extends JPanel {
    private Board board;


    public BoardView(Board board) {
        this.board = board;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBeginingCell(g);

        for (int i = 0; i<board.getSegments().length; i++) {
            drawSegment(g, board.getSegments()[i]);
        }
        drawBoatCell(g);
    }

    private void drawBeginingCell(Graphics g){
        int xPos = 0;
        int yPos = 0;

        g.setColor(Color.black);
        g.fillRect(xPos,yPos, cellWidth(), cellHeight());

        g.setColor(Color.white);
        g.drawString("Begining Cell", 5 ,15);
    }

    private void drawBoatCell(Graphics g){
        //if rowCount() is even boat should be at left bottom - left corner, otherwise at bottom - right
        int xPos = rowCount() % 2 == 0 ? cellWidth() : cellWidth() * columnCount();
        int yPos = rowCount() * cellHeight();
        g.setColor(Color.black);
        g.fillRect(xPos,yPos, cellWidth(), cellHeight());

        g.setColor(Color.white);
        g.drawString("Boat \nCell", xPos + 5, yPos + 15);
    }

    private void drawSegment(Graphics g, Segment segment){
        for(int i = 0; i<segment.getCells().length; i++)
            drawCell(g, segment, segment.getCells()[i]);
    }

    private void drawCell(Graphics g, Segment segment, GameCell cell){
        //if segment index is even draw right to left, otherwise draw left to right
        int multiplier = cell.getIndex();
        if(segment.getIndex() % 2 == 1)
            multiplier = columnCount() - multiplier -1;

        int xPos = cellWidth() * (1 + multiplier);
        int yPos = cellHeight() * segment.getIndex();

        g.setColor(decideColor(cell, Color.green, Color.yellow));
        g.fillRect(xPos,yPos, cellWidth(), cellHeight());

        g.setColor(Color.black);
        g.drawString(String.valueOf(cell.getIndex() + " " + cell.getSymbol()), xPos + 25, yPos + 25);
    }

    private Color decideColor(GameCell cell, Color c1, Color c2){
        if(cell.getSegment().getIndex() % 2 == 1)
            if(cell.getIndex()%2 == cell.getSegment().getIndex()%2)
                return c1;
            else
                return c2;
        else
            if(cell.getIndex()%2 == cell.getSegment().getIndex()%2)
                return c2;
            else
                return c1;
    }

    private int rowCount(){
        return board.getSegments().length;
    }

    private int columnCount(){
        return board.getSegments()[0].getCells().length;
    }

    private int cellWidth(){
        return getWidth() / (columnCount() + 1);
    }

    private int cellHeight(){
        return getHeight() / (rowCount() + 1);
    }

}
