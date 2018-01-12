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

    private static List<Player> generatePlayerList(int playerCount, Deck deck){
        List<Player> players = new ArrayList<>(playerCount);
        for(int i = 0; i < playerCount; i++){
            List<Pirate> pirateList = new ArrayList<>(6);
            for(int j=0; j<6; j++){
                Pirate p = new Pirate();
                p.move(BeginCell.getInstance());
                pirateList.add(p);
            }

            List<Symbol> symbolList = new ArrayList<>(6);
            for(int j = 0; j < 6; j++){
                symbolList.add(deck.getTopCard());
            }

            Player player = new Player(i, pirateList, symbolList);
            players.add(player);
        }
        return players;
    }

}
