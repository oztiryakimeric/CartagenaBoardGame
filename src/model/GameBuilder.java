package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class GameBuilder {
    public static Game buildSingle(int playerCount, int segmentCount, List<Symbol> symbolList){
        List<Player> playerList = generatePlayerList(playerCount, Deck.getInstance(symbolList));
        Board board = BoardBuilder.build(segmentCount, symbolList);

        Game game = new Game();
        game.setPlayerList(playerList);
        game.setBoard(board);
        game.setDeck(Deck.getInstance(symbolList));
        return game;
    }

    public static Game buildMulti(){
        return new Game();
    }

    public static List<Player> generatePlayerList(int playerCount, Deck deck){
        List<Player> players = new ArrayList<>(playerCount);
        int pirateCounter = 0;
        for(int i = 0; i < playerCount; i++){
            List<Pirate> pirateList = new ArrayList<>(6);
            for(int j=0; j<6; j++){
                Pirate p = new Pirate(pirateCounter++);
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
