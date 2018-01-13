package gui;

import gui.setup.SetupController;
import gui.setup.SetupView;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SetupView setupView = new SetupView();
        SetupController controller = new SetupController(setupView);
        controller.start();

        /*IGame game = GameBuilder.buildSingle(2, 6, Symbol.getSymbols());

        game = new MultiplayerGame(game);
        GameView gui = new GameView(game);

        GameController controller = new GameController(gui, game);
        controller.start();*/
    }
}
