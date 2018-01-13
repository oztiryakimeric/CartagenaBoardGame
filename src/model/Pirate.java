package model;

import java.io.Serializable;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */

public class Pirate  implements Serializable {
    private int id;

    private Cell cell;

    public Pirate(int id) {
        this.id = id;
        cell = BeginCell.getInstance();
    }

    public void move(Cell cell){

        this.cell = cell;
    }

    public int getId() {
        return id;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Pirate) obj).getId();
    }
}
