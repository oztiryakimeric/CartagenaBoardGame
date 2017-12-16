package gui;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class BoardView extends JPanel {

    private final Game game;

    public BoardView(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPawns(g);
        drawBoard(g);
    }

    private void drawPawns(Graphics g){
        for(Player p: game.getPlayerList()){
            drawPawns(g, p.getColor(), p.getPirates());
        }
    }

    private void drawPawns(Graphics g, Color c, Pirate[] pirates){
        for(int i = 0; i< pirates.length; i++){

        }
    }

    private void drawBoard(Graphics g){
        drawBeginingCell(g);
        for (int i = 0; i<game.getBoard().getSegments().length; i++)
            drawSegment(g, game.getBoard().getSegments()[i]);
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
        Point position = cellPosition(cell);

        g.setColor(decideCellColor(cell, Color.green, Color.yellow));
        g.fillRect(position.x, position.y, cellWidth(), cellHeight());

        g.setColor(Color.black);
        g.drawString(String.valueOf(cell.getIndex() + cell.getSegment().getIndex() * columnCount() + " " + cell.getSymbol()), position.x + 25, position.y + 25);
    }

    //Not working if column count is odd
    private Color decideCellColor(GameCell cell, Color c1, Color c2){
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
        return game.getBoard().getSegments().length;
    }

    private int columnCount(){
        return game.getBoard().getSegments()[0].getCells().length;
    }

    private int cellWidth(){
        return getWidth() / (columnCount() + 1);
    }

    private int cellHeight(){
        return getHeight() / (rowCount() + 1);
    }

    private Point cellPosition(GameCell c){
        int multiplier = c.getIndex();
        if(c.getSegment().getIndex() % 2 == 1)
            multiplier = columnCount() - multiplier -1;

        int xPos = cellWidth() * (1 + multiplier);
        int yPos = cellHeight() * c.getSegment().getIndex();
        return new Point(xPos, yPos);
    }

}
