package gui;

import model.Game;
import model.GameCell;
import model.Segment;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 16.12.2017.
 */
public class NewBoardView extends JPanel {

    private Game game;

    public NewBoardView(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        new EmptyBoard(g, Color.red, Color.black).draw();
        drawCells(g);
    }

    private void drawCells(Graphics g){
        for(int i=0; i<game.getBoard().getSegments().length; i++){
            Segment segment = game.getBoard().getSegments()[i];
            for(int j=0; j<segment.getCells().length; j++){
                GameCell cell = segment.getCells()[j];
                Point position = getPosition(cell);
                g.drawString(cell.getIndex() + " " + cell.getSymbol().toString(), position.x + 50, position.y + 30);
            }
        }
    }

    private Point getPosition(GameCell cell){
        int multiplier = cell.getIndex();
        if(cell.getSegment().getIndex() % 2 == 1)
            multiplier = columnCount() - multiplier -1;

        int xPos = cellWidth() * (1 + multiplier);
        int yPos = cellHeight() * cell.getSegment().getIndex();
        return new Point(xPos, yPos);
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

    private class EmptyBoard{
        private static final double BORDER_RATIO = 7.0;

        private Graphics graphics;
        private Point point;
        private Color pathColor;
        private Color borderColor;

        public EmptyBoard(Graphics graphics, Color pathColor, Color borderColor) {
            this.graphics = graphics;
            this.point = new Point(cellWidth(), 0);
            this.pathColor = pathColor;
            this.borderColor = borderColor;
        }

        public void draw(){
            drawPathColor();
            drawGrid();
            drawBegining();
            drawBoat();
            drawLeftAndRightBorders();
            drawTopSegment();
            drawMiddleSegments();
        }

        private void drawPathColor(){
            graphics.setColor(pathColor);
            graphics.fillRect(point.x, point.y, getWidth(), getHeight() - cellHeight());
        }

        private void drawGrid(){
            graphics.setColor(Color.gray);

            //Draw dikey lines
            int xPos = point.x;
            for(int i=0; i<columnCount(); i++){
                graphics.drawLine(xPos, point.y, xPos, getHeight() - cellHeight());
                xPos += cellWidth();
            }

            //Draw yatay lines
            int yPos = point.y;
            for(int i=0; i<=rowCount(); i++){
                graphics.drawLine(point.x, yPos, getWidth(), yPos);
                yPos += cellHeight();
            }
        }

        private void drawBegining(){
            graphics.setColor(Color.white);

            graphics.fillRect(0,0, cellWidth(), cellHeight());

            //draw borders
            graphics.setColor(Color.black);
            graphics.fillRect(0,0, cellWidth(), borderHeight());
            graphics.fillRect(0,0, borderWidth(), cellHeight());
            graphics.fillRect(0, middleBottomBorderYPosition(0), cellWidth(), borderHeight() + 3);

            graphics.drawString("Begining cell", 40, 30);
        }

        private void drawBoat(){
            
        }

        private void drawTopSegment(){
            graphics.setColor(borderColor);
            //Draw top border
            graphics.fillRect(point.x, point.y, getWidth(), borderHeight());

            //draw bottom border
            int yPos = middleBottomBorderYPosition(0);
            graphics.fillRect(point.x,  yPos, middleBorderWidth(), borderHeight() + 3);


        }

        private void drawLeftAndRightBorders(){
            graphics.setColor(borderColor);
            //draw left border
            graphics.fillRect(point.x, point.y + cellHeight(), borderWidth(), cellHeight() * (rowCount() -1));

            //draw rigth border
            graphics.fillRect(getWidth() - borderWidth(), point.y, borderWidth(), cellHeight() * rowCount());
        }

        private void drawMiddleSegments(){
            for(int i=1; i<=rowCount() - 1; i++){
                if(i%2 == 0)
                    drawRighToLeftSegment(i);
                else
                    drawLeftToRightSegment(i);
            }
        }

        private void drawLeftToRightSegment(int segmentIndex){
            graphics.setColor(borderColor);
            //draw top border
            int yPos = cellHeight() * segmentIndex;
            graphics.fillRect(point.x, yPos, middleBorderWidth(), borderHeight());

            //draw bottom border
            int xPos = point.x + cellWidth() - borderWidth();
            yPos = middleBottomBorderYPosition(segmentIndex);
            graphics.fillRect(xPos, yPos, middleBorderWidth(), borderHeight() + 3);

        }

        private void drawRighToLeftSegment(int segmentIndex){
            graphics.setColor(borderColor);

            //draw top border
            int xPos = point.x + cellWidth() - borderWidth();
            int yPos = cellHeight() * segmentIndex;
            graphics.fillRect(xPos, yPos, middleBorderWidth(), borderHeight());

            //draw bottom border
            xPos = point.x;
            yPos = middleBottomBorderYPosition(segmentIndex);
            graphics.fillRect(xPos, yPos, middleBorderWidth(), borderHeight() + 3);
        }

        private int borderWidth(){
            return cellWidth() /(int) BORDER_RATIO;
        }

        private int borderHeight(){
            return cellHeight() /(int) BORDER_RATIO;
        }

        private int middleBorderWidth(){
            return cellWidth() * (rowCount() - 1) + borderWidth();
        }

        private int middleBottomBorderYPosition(int index){
            return (int) ((BORDER_RATIO - 1) / BORDER_RATIO * cellHeight() + point.y) + cellHeight() * index;
        }
    }
}




