package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class GameBuilder {

    public static Game build(int playerCount, int segmentCount, List<Symbol> symbolList){
        List<Player> playerList = generatePlayerList(playerCount, Deck.getInstance());
        Board board = BoardBuilder.build(segmentCount, symbolList);

        return new Game(playerList, board, Deck.getInstance());
    }

    private static List<Player> generatePlayerList(int count, Deck deck){
        List<Player> players = new ArrayList<>(count);
        for(int i = 0; i < count; i++){
            Player player = new Player(i);

            for(int j = 0; j < 6; j++)
                player.addCard(deck.getTopCard());

            players.add(player);
        }
        return players;
    }

}
