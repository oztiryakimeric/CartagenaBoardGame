package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by oztiryakimeric on 16.12.2017.
 */
public class BoardView extends JPanel {

    private Game game;
    private CellSelectListener listener;
    private List<HighlightedCell> highlightedCellList;
    private boolean enabled = true;


    public BoardView(Game game) {
        this.game = game;
        highlightedCellList = new ArrayList<>();

        startListener();
    }

    private void startListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Cell cell = getCell(new Point(e.getX(), e.getY()));
                if(isEnabled() && listener != null && cell != null){
                    listener.cellSelected(cell);
                }
            }
        });
    }

    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public void highlightCell(Cell cell, Color c){
        highlightedCellList.add(new HighlightedCell(cell, c));
        this.repaint();
    }

    public void removeHighlights(){
        highlightedCellList.clear();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        new BoardDrawer(g, Color.orange, Color.black).draw();
        new PirateDrawer(g).draw();
    }

    private Point getPosition(Cell cell){
        if(cell instanceof GameCell){
            GameCell c = (GameCell) cell;
            int multiplier = cell.getIndex() % game.getBoard().getSegments()[0].getCells().length;
            if(c.getSegment().getIndex() % 2 == 1)
                multiplier = columnCount() - multiplier -1;

            int xPos = cellWidth() * (1 + multiplier);
            int yPos = cellHeight() * c.getSegment().getIndex();
            return new Point(xPos, yPos);
        }
        else if(cell instanceof BeginCell){
            return new Point(0,0);
        }
        else if(cell instanceof BoatCell){
            int xPos = rowCount() % 2 == 0 ? cellWidth() : cellWidth() * columnCount();
            int yPos = rowCount() * cellHeight();
            return new Point(xPos, yPos);
        }
        return null;
    }

    private Cell getCell(Point position) throws ArrayIndexOutOfBoundsException{
        int segmentIndex = position.y / cellHeight();
        int cellIndex = position.x / cellWidth() - 1;
        int realIndex = cellIndex;

        if(segmentIndex % 2 == 1)
            cellIndex = columnCount() - cellIndex - 1;


        if(realIndex > -1 && segmentIndex < rowCount())
            return game.getBoard().getSegments()[segmentIndex].getCells()[cellIndex];
        else if(realIndex == -1 && segmentIndex == 0)
            return game.getBoard().getBeginingCell();
        else if((rowCount() % 2) == 0 && realIndex == 0 && segmentIndex == rowCount())
            return game.getBoard().getBoatCell();
        else if((rowCount() % 2) == 1 && realIndex == columnCount() - 1 && segmentIndex == rowCount())
            return game.getBoard().getBoatCell();
        else
            return null;
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

    public void addCellSelectListener(CellSelectListener listener){
        this.listener = listener;
    }

    private class BoardDrawer {
        private static final double BORDER_RATIO = 9.0;

        private Graphics graphics;
        private Point point;
        private Color pathColor;
        private Color borderColor;

        public BoardDrawer(Graphics graphics, Color pathColor, Color borderColor) {
            this.graphics = graphics;
            this.point = new Point(cellWidth(), 0);
            this.pathColor = pathColor;
            this.borderColor = borderColor;

        }

        public void draw(){
            drawBegining();
            drawBoat();
            drawPathColor();
            drawGrid();
            drawHighlightedCells();
            drawOuterBorders();
            drawMiddleSegments();
            drawCellsInfo();
        }

        private void drawPathColor(){
            graphics.setColor(pathColor);
            graphics.fillRect(point.x, point.y, getWidth(), getHeight() - cellHeight());
        }

        private void drawHighlightedCells(){
            for(HighlightedCell highlighted: highlightedCellList){
                Point position = getPosition(highlighted.getCell());
                graphics.setColor(highlighted.getColor());
                graphics.fillRect(position.x, position.y, cellWidth(), cellHeight());
            }
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
        }

        private void drawBoat(){
            //if rowCount() is even boat should be at left bottom - left corner, otherwise at bottom - right
            Point position = getPosition(game.getBoard().getBoatCell());

            graphics.setColor(Color.white);
            graphics.fillRect(position.x, position.y, cellWidth(), cellHeight());
        }

        private void drawOuterBorders(){
            graphics.setColor(borderColor);
            //draw top border
            graphics.fillRect(point.x, point.y, cellWidth() * columnCount(), borderHeight());

            //draw left border
            graphics.fillRect(point.x, point.y + cellHeight(), borderWidth(), cellHeight() * (rowCount() -1));

            //draw rigth border
            graphics.fillRect(getWidth() - borderWidth(), point.y, borderWidth(), cellHeight() * rowCount());

            //begining cell
            graphics.setColor(Color.black);
            graphics.fillRect(0,0, cellWidth(), borderHeight());
            graphics.fillRect(0,0, borderWidth(), cellHeight());
            graphics.fillRect(0, 0 + cellHeight() - borderHeight(), cellWidth(), borderHeight());

            //boat cell
            Point position = getPosition(game.getBoard().getBoatCell());
            graphics.setColor(Color.black);
            graphics.fillRect(position.x, position.y, borderWidth(), cellHeight());
            graphics.fillRect(position.x, position.y + cellHeight() - borderHeight(), cellWidth(), borderHeight());
            graphics.fillRect(position.x + cellWidth() - borderWidth(), position.y, borderWidth(), cellHeight());
        }

        private void drawMiddleSegments(){
            graphics.setColor(borderColor);
            for(int i=0; i<=rowCount() - 1; i++){
                if(i%2 == 0)
                    drawRighToLeftSegment(i);
                else
                    drawLeftToRightSegment(i);
            }
        }

        private void drawLeftToRightSegment(int segmentIndex){
            //draw top border
            int yPos = cellHeight() * segmentIndex;
            graphics.fillRect(point.x, yPos, middleBorderWidth(), borderHeight());

            //draw bottom border
            int xPos = point.x + cellWidth() - borderWidth();
            yPos = point.y + segmentIndex * cellHeight() + cellHeight() - borderHeight();
            graphics.fillRect(xPos, yPos, middleBorderWidth(), borderHeight());
        }

        private void drawRighToLeftSegment(int segmentIndex){
            //draw top border
            int xPos = point.x + cellWidth() - borderWidth();
            int yPos = cellHeight() * segmentIndex;
            graphics.fillRect(xPos, yPos, middleBorderWidth(), borderHeight());

            //draw bottom border
            xPos = point.x;
            yPos = point.y + segmentIndex * cellHeight() + cellHeight() - borderHeight();
            graphics.fillRect(xPos, yPos, middleBorderWidth(), borderHeight());
        }

        private void drawCellsInfo(){
            for(int i = 0; i < game.getBoard().getSegments().length; i++){
                Segment segment = game.getBoard().getSegments()[i];
                for(int j = 0; j < segment.getCells().length; j++){
                    GameCell cell = segment.getCells()[j];
                    Point position = getPosition(cell);
                    graphics.drawString(alignStringsOfCells(cell.getSymbol().toString()), position.x + cellWidth() / 6 - 5, position.y + 30);
                }
            }

            //begining
            graphics.drawString(alignStringsOfCells("Start Here!"), 0 + cellWidth() / 6, 0 + 30);

            //boat
            Point position = getPosition(game.getBoard().getBoatCell());
            graphics.drawString(alignStringsOfCells("BOAT"), position.x + cellWidth() / 6, position.y + 30);
        }

        private String alignStringsOfCells(String str){
            int length = str.length();
            String newStr = "";
            for(int i = 0; i < 10 - length / 2; i++)
                newStr += " ";
            newStr += str;
            for(int i = 0; i < 10 - length / 2; i++)
                newStr += " ";
            return newStr;
        }

        private int borderWidth(){
            return cellWidth() /(int) BORDER_RATIO;
        }

        private int borderHeight(){
            return cellHeight() /(int) BORDER_RATIO;
        }

        private int middleBorderWidth(){
            return cellWidth() * (columnCount() - 1) + borderWidth();
        }
    }

    private class PirateDrawer{
        private static final double PAWN_RATIO = 5.0;
        private Graphics graphics;

        public PirateDrawer(Graphics graphics) {
            this.graphics = graphics;
        }

        public void draw(){
            drawPlayers();
        }

        public void drawPlayers(){
            for(Player player: game.getPlayerList()){
                drawPlayer(player);
            }
        }

        private void drawPlayer(Player p){
            HashMap<Cell, Integer> map = mapCells(p.getPirateList());
            Iterator it = map.entrySet().iterator();

            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                Cell cell = (Cell) pair.getKey();
                int count = (int) pair.getValue();
                drawPirate(p, cell, count);
                it.remove();
            }
        }

        private void drawPirate(Player p, Cell cell, int count){
            Point cellPosition = getPosition(cell);
            Point startingPosition = new Point(cellPosition.x + pawnWidth(), cellPosition.y + pawnHeight()*2);
            int xPos = (p.getId() % 3) * (pawnWidth() + marginWidth()) + startingPosition.x;
            int yPos = ((p.getId() / 3) * (pawnHeight() + marginHeight())) + startingPosition.y;

            graphics.setColor(p.getColor());
            graphics.fillOval(xPos, yPos, pawnWidth(), pawnHeight());

            graphics.setColor(Color.black);
            graphics.drawString(String.valueOf(count), xPos + pawnWidth() / 2 - 5, yPos + pawnHeight() / 2 + 5);
        }

        private HashMap<Cell, Integer> mapCells(List<Pirate> pirateList){
            HashMap<Cell, Integer> cellMap = new HashMap<>();
            for(Pirate pirate: pirateList){
                int count = cellMap.getOrDefault(pirate.getCell(), 0);
                cellMap.put(pirate.getCell(), count+1);
            }
            return cellMap;
        }

        private int pawnWidth(){
            return cellWidth() /(int) PAWN_RATIO;
        }

        private int pawnHeight(){
            return cellHeight() /(int) PAWN_RATIO;
        }

        private int marginWidth(){
            return pawnWidth() / 4;
        }

        private int marginHeight(){
            return pawnHeight() / 4;
        }
    }
    public interface CellSelectListener{
        void cellSelected(Cell cell);
    }

    public class HighlightedCell{
        private Cell cell;
        private Color color;

        public HighlightedCell(Cell cell, Color color) {
            this.cell = cell;
            this.color = color;
        }

        public Cell getCell() {
            return cell;
        }

        public Color getColor() {
            return color;
        }
    }
}




