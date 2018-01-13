package model;

import java.io.Serializable;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */

public class Pirate  implements Serializable {
    private int id;
    private static int idCounter = 0;

    private Cell cell;

    public Pirate() {
        cell = BeginCell.getInstance();
    }

    public void move(Cell cell){
        this.id = idCounter++;
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }
}
