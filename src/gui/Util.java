package gui;

import java.awt.*;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class Util {
    public static Color idToColor(int id){
        switch(id){
            case 0:
                return Color.cyan;

            case 1:
                return Color.MAGENTA;
            case 2:
                return Color.red;
            case 3:
                return Color.green;
            case 4:
                return Color.pink;
        }
        return null;
    }
}
