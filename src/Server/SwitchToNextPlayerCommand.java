package Server;

import model.MultiplayerGame;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public class SwitchToNextPlayerCommand implements Command {

    private int sender;

    public SwitchToNextPlayerCommand(int sender) {
        this.sender = sender;
    }

    @Override
    public void executeOn(MultiplayerGame game) {
        game.switchToNextPlayerWithBroadcast(false);
    }

    @Override
    public int getSenderId() {
        return sender;
    }
}
