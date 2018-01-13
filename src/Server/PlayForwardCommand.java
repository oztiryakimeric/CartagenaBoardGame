package Server;

import model.IGame;
import model.Pirate;
import model.Symbol;

public class PlayForwardCommand implements Command{
    private Pirate pirate;
    private Symbol symbol;

    public PlayForwardCommand(Pirate pirate, Symbol symbol) {
        this.pirate = pirate;
        this.symbol = symbol;
    }

    @Override
    public void executeOn(IGame game) {
        game.playForward(pirate, symbol);
    }
}
