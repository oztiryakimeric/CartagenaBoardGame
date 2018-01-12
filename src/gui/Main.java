package gui;

import model.Game;
import model.GameBuilder;
import model.Symbol;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Main {
    public static void main(String[] args){
        Game game = GameBuilder.build(5, 6, Symbol.getSymbols());

        Gui gui = new Gui(game);

        Controller controller = new Controller(gui, game);
        controller.start();
    }
}
