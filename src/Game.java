import java.awt.*;
import java.util.LinkedList;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Game {
    private LinkedList<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private int numPlayers;
    private int turnCount;

    public Game(int numPlayers, Board board){
        this.board = board;
        this.numPlayers = numPlayers;
        turnCount = 0;
        initPlayers();
    }

    //Color needs to be changed
    private void initPlayers(){
        for(int i = 0; i < numPlayers; i++){
            playerList.add(new Player(i, Color.BLACK));
        }
    }

    public void move(Pawn pawn, Symbol symbol, Cell cell){
        int pawnIndex = pawn.getIndex();
        for(int i = pawnIndex; i < pawnIndex + 6; i++){
            if( )
        }
    }

    public Player currentPlayer(){
        return currentPlayer;
    }

    public void switchToNextPlayer(){
        currentPlayer = playerList.get(turnCount % numPlayers);
    }

    public boolean isFinished() {
        return getWinner() != null;
    }

    public Player getWinner() {
        for (Player player: getPlayers()) {
            if (player.getInBoat() == 6)
                return player;
        }
        return null;
    }

    public LinkedList<Player> getPlayers() {
        return playerList;
    }
}
