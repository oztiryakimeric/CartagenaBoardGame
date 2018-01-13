package model;

import java.io.Serializable;

public class BeginCell extends Cell{
    private static BeginCell instance;

    public static BeginCell getInstance(){
        if(instance == null)
            instance = new BeginCell();
        return instance;
    }

    private BeginCell() {
        super(-1);
    }

    @Override
    public String toString() {
        return "BEGIN CELL";
    }
}
