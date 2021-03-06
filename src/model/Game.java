package model;

import gui.game.GameController;

import java.util.List;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Game implements IGame{
    private List<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private Deck deck;

    @Override
    public void setBoard(Board board){
        this.board = board;
    }

    @Override
    public void setPlayerList(List<Player> playerList){
        this.playerList = playerList;
        this.currentPlayer = playerList.get(0);
    }

    @Override
    public void setDeck(Deck deck){
        this.deck = deck;
    }

    @Override
    public void setController(GameController controller) {

    }

    @Override
    public void playForward(Pirate pirate, Symbol symbol){
        currentPlayer.discard(symbol);
        pirate = findRealPirateObject(pirate);
        Cell destinationCell = board.findPossibleForwardCell(pirate, symbol);

        pirate.getCell().pirateLeft();
        destinationCell.pirateCame();

        pirate.move(destinationCell);
    }

    @Override
    public void playBackward(Pirate pirate){
        pirate = findRealPirateObject(pirate);
        Cell destinationCell = board.findPossibleBackwardCell(pirate);

        for(int i = 0; i < destinationCell.getPirateCount(); i++){
            currentPlayer.addCard(deck.getTopCard());
        }

        pirate.getCell().pirateLeft();
        destinationCell.pirateCame();

        pirate.move(destinationCell);
    }

    @Override
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    @Override
    public void switchToNextPlayer(){
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    @Override
    public boolean isFinished() {
        return currentPlayer.isWinner();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public List<Player> getPlayerList() {
        return playerList;
    }

    private Pirate findRealPirateObject(Pirate p){
        for(Player player: playerList){
            for(Pirate pirate: player.getPirateList()){
                if(pirate.equals(p))
                    return pirate;
            }
        }
        return null;
    }
}
