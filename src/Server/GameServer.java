package Server;

import model.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public class GameServer extends Server{

    private Board board;
    private Deck deck;
    private List<Player> playerList;

    public GameServer(int playerCount, int segmentCount, List<Symbol> symbolList) throws IOException {
        super(playerCount);

        this.board = BoardBuilder.build(segmentCount, symbolList);
        this.deck = Deck.getInstance(Symbol.getSymbols());
        this.playerList = GameBuilder.generatePlayerList(playerCount, deck);

        for(Player player: playerList){
            send(player.getId(), new GameRequirementsCommand(board, deck, playerList, player.getId()));
        }
    }
}
