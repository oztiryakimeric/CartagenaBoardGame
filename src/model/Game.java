package model;

import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        board = Board.getInstance(6);
        initDeck();
        initPlayers();

        //playRandomly(10);
    }

    private void playRandomly(int playCount){
        Random r = new Random();
        int turn = 0;
        while(playCount != 0 && currentPlayer.getDeck().size() > 0){

            Pirate p = currentPlayer.getPirateList().get(r.nextInt(currentPlayer.getPirateList().size()));
            Symbol s = currentPlayer.getDeck().get(r.nextInt(currentPlayer.getDeck().size()));

            playForward(p, s);

            if(turn++ == 2)
                switchToNextPlayer();

            playCount--;
        }
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

        currentPlayer = playerList.get(0);
    }

    public void playForward(Pirate pirate, Symbol symbol){
        currentPlayer.discard(symbol);

        Cell destinationCell = board.getForwardPossibleCell(pirate, symbol);

        pirate.getCell().pirateLeft();
        destinationCell.pirateCame();

        pirate.move(destinationCell);
    }

    public void playBackward(Pirate pirate){
        Cell destinationCell = board.getBackwardPossibleCell(pirate);
        System.out.println("Found cell Segment: " + ((GameCell) destinationCell).getSegment().getIndex() + " Cell: " + destinationCell.getIndex());

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
