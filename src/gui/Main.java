package gui;

import model.Game;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Main {
    public static void main(String[] args){
        Game game = new Game(5);

        Gui gui = new Gui(game);

        Controller controller = new Controller(gui, game);
        controller.start();
    }
}
