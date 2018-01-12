package model;

import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Game {
    private List<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private Deck deck;

    public Game(List<Player> playerList, Board board, Deck deck){
        this.playerList = playerList;
        this.board = board;
        this.deck = deck;
        currentPlayer = playerList.get(0);
    }

    public void playForward(Pirate pirate, Symbol symbol){
        currentPlayer.discard(symbol);

        Cell destinationCell = board.findPossibleForwardCell(pirate, symbol);

        pirate.getCell().pirateLeft();
        destinationCell.pirateCame();

        pirate.move(destinationCell);
    }

    public void playBackward(Pirate pirate){
        Cell destinationCell = board.findPossibleBackwardCell(pirate);

        for(int i = 0; i < destinationCell.getPirateCount(); i++){
            currentPlayer.addCard(deck.getTopCard());
        }

        pirate.getCell().pirateLeft();
        destinationCell.pirateCame();

        pirate.move(destinationCell);
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void switchToNextPlayer(){
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());


    }

    public boolean isFinished() {
        return currentPlayer.isWinner();
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
