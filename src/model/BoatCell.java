package model;

public class BoatCell extends Cell{
    private static BoatCell instance;

    public static BoatCell getInstance(){
        if(instance == null)
            instance = new BoatCell();
        return instance;
    }

    private BoatCell() {
        super(-1);
    }

    @Override
    public String toString() {
        return "BOAT";
    }
}
