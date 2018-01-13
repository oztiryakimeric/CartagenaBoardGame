package Server;

import model.IGame;
import model.Player;

import java.util.List;

public class SetPlayerListCommand implements Command{
    private List<Player> playerList;

    public SetPlayerListCommand(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public void executeOn(IGame game) {
        game.setPlayerList(playerList);
    }
}
