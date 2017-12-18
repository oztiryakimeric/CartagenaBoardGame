package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Game {
    private List<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private Deck deck;
    private int numPlayers;

    public Game(int numPlayers){
        this.numPlayers = numPlayers;
        playerList = new ArrayList<>();
        board = Board.getInstance(7);
        initDeck();
        initPlayers();
    }

    private void initDeck(){
        deck = Deck.getInstance();
    }

    private void initPlayers(){
        for(int i = 0; i < numPlayers; i++){
            Player player = new Player(i);

            for(int j = 0; j < 6; j++)
                player.addCard(deck.getTopCard());

            playerList.add(player);
        }
    }

    public void move(Pirate pirate, Symbol symbol){
        playRound(pirate, symbol);
        boolean wantSecondRound = false;

        if(!wantSecondRound){
            getCurrentPlayer().addCard(deck.getTopCard());
            getCurrentPlayer().addCard(deck.getTopCard());
        } else {

            getCurrentPlayer().addCard(deck.getTopCard());
        }

        if(!isFinished()) {
            switchToNextPlayer();
        }
    }

    private void playRound(Pirate pirate, Symbol symbol){
        Cell destinationCell = board.getPossibleCell(pirate, symbol);
        pirate.move(destinationCell);
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void switchToNextPlayer(){
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    public boolean isFinished() {
        return !currentPlayer.isWinner();
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }



    @Override
    public String toString() {
        return "model.Game{" +
                "board=" + board +
                '}';
    }
}
