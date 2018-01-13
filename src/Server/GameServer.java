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

    public static void main(String[] args) throws IOException{
        new GameServer(2, 6, Symbol.getSymbols());
    }

    public GameServer(int playerCount, int segmentCount, List<Symbol> symbolList) throws IOException {
        super(playerCount);

        this.board = BoardBuilder.build(segmentCount, symbolList);
        this.deck = Deck.getInstance(Symbol.getSymbols());
        this.playerList = GameBuilder.generatePlayerList(playerCount, deck);

        System.out.println("Broadcasting game data");
        broadcast(new SetBoardCommand(this.board));
        broadcast(new SetDeckCommand(this.deck));
        broadcast(new SetPlayerListCommand(this.playerList));
        System.out.println("Broadcasting game data completed");
    }
}
