package model;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */

public class Pirate {
    private Cell cell;

    public Pirate() {
        cell = BeginCell.getInstance();
    }

    public void move(Cell cell){
        cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
