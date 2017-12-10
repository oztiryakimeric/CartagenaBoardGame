import java.awt.*;
import java.util.LinkedList;

/**
 * Created by oztiryakimeric on 9.12.2017.
 */
public class Game {
    private LinkedList<Player> playerList;
    private Player currentPlayer;
    private Board board;
    private Deck deck;
    private int numPlayers;
    private int turnCount;

    public Game(int numPlayers){
        this.numPlayers = numPlayers;
        board = Board.getInstance();
        turnCount = 0; //Bu turn count sayesinde kimin sırasının geldiğini öğrenicez
        initDeck();
        initPlayers();
    }

    private void initDeck(){
        deck = Deck.getInstance();
    }


    private void initPlayers(){
        for(int i = 0; i < numPlayers; i++){
            playerList.add(new Player(i));

            //Deck'ten 6 kart alıp oyunculara ekliyor
            for(int j = 0; j < 6; j++){
                playerList.get(i).addCard(deck.getTopCard());
            }
        }
    }

    //hangi segmentte olduğumuzu bilmediğimiz için segmente göre cell aramak çok kullanışlı değil. Bir çözüm bulmak gerek.
    //Burası çok eksik çözümü bulamadım
    public void move(Pawn pawn, Symbol symbol){
        int pawnIndex = pawn.getIndex();
        for(int i = pawnIndex; i < pawnIndex + 6; i++){

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
