package model;

public class BeginCell extends Cell{
    private static BeginCell instance;

    public static BeginCell getInstance(){
        if(instance == null)
            instance = new BeginCell();
        return instance;
    }

    private BeginCell() {
        super(0);
    }

    @Override
    public String toString() {
        return "BEGIN CELL";
    }
}
