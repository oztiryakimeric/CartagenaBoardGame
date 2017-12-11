import java.util.ArrayList;
import java.util.LinkedList;
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
        board = Board.getInstance();
        initDeck();
        initPlayers();
    }

    private void initDeck(){
        deck = Deck.getInstance();
    }


    private void initPlayers(){
        for(int i = 0; i < numPlayers; i++){
            //burda ufak bi hata vardı, duzelttim.
            Player player = new Player(i);

            for(int j = 0; j < 6; j++)
                player.addCard(deck.getTopCard());

            playerList.add(player);
        }
    }

    /*Segmentle aramaya bir çözüm buldum. Ancak Pawn ve symbol inputlarını nasıl vereceğimizi bir konuşalım.
      İki tur hakkını oyuncu nasıl kullanacak düşünmekte fayda var.
      şimdilik move bir şekilde pawn ve karttan gelecek symbolünü alıyor. Ona göre bir move işlemi yapıyor.
      Ayrıca Symbol yerine Card mı alsa acaba move methodu?
     */
    public void move(Pawn pawn, Symbol symbol){
        playRound(pawn, symbol);
        boolean wantSecondRound = false;

        if(!wantSecondRound){
            currentPlayer().addCard(deck.getTopCard());
            currentPlayer().addCard(deck.getTopCard());
        } else {
            playRound(pawn, symbol);
            currentPlayer().addCard(deck.getTopCard());
        }

        if(!isFinished()) {
            switchToNextPlayer();
        }
    }

    private void playRound(Pawn pawn, Symbol symbol){
        //burayı alt classlara tasıdım
        //burada board classına bu pawn ve sembol ıcın mumkun bi cell ver diyorum.
        Cell destinationCell = board.getPossibleCell(pawn, symbol);
        pawn.move(destinationCell);
    }

    public Player currentPlayer(){
        return currentPlayer;
    }

    public void switchToNextPlayer(){
        //turn count'a gerek kalmadan boyle hesaplayabiliriz.
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    public boolean isFinished() {
        return !currentPlayer.isWinner();
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                '}';
    }
}
