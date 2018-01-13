package Server;

import model.MultiplayerGame;
import model.Pirate;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public class PlayBackwardCommand implements Command{

    private int sender;
    private Pirate pirate;

    public PlayBackwardCommand(int sender, Pirate pirate) {
        this.sender = sender;
        this.pirate = pirate;
    }

    @Override
    public void executeOn(MultiplayerGame game) {
        game.playBackwardWithBroadcast(pirate, false);
    }

    @Override
    public int getSenderId() {
        return sender;
    }
}
