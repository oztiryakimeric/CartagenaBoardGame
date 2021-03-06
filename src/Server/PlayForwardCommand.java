package Server;

import model.MultiplayerGame;
import model.Pirate;
import model.Symbol;

public class PlayForwardCommand implements Command{

    private int sender;
    private Pirate pirate;
    private Symbol symbol;

    public PlayForwardCommand(int sender, Pirate pirate, Symbol symbol) {
        this.sender = sender;
        this.pirate = pirate;
        this.symbol = symbol;
    }

    @Override
    public void executeOn(MultiplayerGame game) {
        game.playForwardWithBroadcast(pirate, symbol, false);
    }

    @Override
    public int getSenderId() {
        return sender;
    }
}
