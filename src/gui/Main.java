package gui;

import model.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Main {
    public static void main(String[] args){
        IGame game = GameBuilder.build(2, 6, Symbol.getSymbols());

        game = new MultiplayerGame(game);
        Gui gui = new Gui(game);

        Controller controller = new Controller(gui, game);
        controller.start();
    }
}
