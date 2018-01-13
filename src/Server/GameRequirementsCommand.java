package Server;

import model.Board;
import model.Deck;
import model.MultiplayerGame;
import model.Player;

import java.util.List;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public class GameRequirementsCommand implements Command{
    private Board board;
    private Deck deck;
    private List<Player> playerList;
    private int clientId;

    public GameRequirementsCommand(Board board, Deck deck, List<Player> playerList, int clientId) {
        this.board = board;
        this.deck = deck;
        this.playerList = playerList;
        this.clientId = clientId;
    }


    @Override
    public void executeOn(MultiplayerGame game) {
        game.setDeck(deck);
        game.setPlayerList(playerList);
        game.setBoard(board);
        game.setPlayerId(clientId);
    }

    @Override
    public int getSenderId() {
        return -1;
    }
}
