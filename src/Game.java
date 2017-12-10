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

    public Game(int numPlayers){
        this.numPlayers = numPlayers;
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
            Player p = new Player(i);

            for(int j = 0; j < 6; j++)
                playerList.get(i).addCard(deck.getTopCard());

            playerList.add(p);
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
        int pawnIndex = pawn.getIndex();
        int pawnSegment = pawnIndex / 6;
        int pawnIndexOfSegment = pawnIndex % 6;
        boolean symbolFound = false;

        for(int i = pawnSegment; i < 6; i++){
            for(int j = pawnIndexOfSegment + 1; j < 6; j++){
                if(board.getSegmentList()[i].getCellList().get(j).getSymbol() == symbol){
                    pawn.setIndex((i * 6) + j);
                    symbolFound = true;
                    break;
                }
            }
        }

        if(!symbolFound){
            currentPlayer().moveBoat(pawn);
        }
    }

    public Player currentPlayer(){
        return currentPlayer;
    }

    public void switchToNextPlayer(){
        //turn count'a gerek kalmadan boyle hesaplayabiliriz.
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    public boolean isFinished() {
        //bu kısmı playera tasıdım. Pawn ın boat dite arraylist e gerek kalmadı
        return currentPlayer.isWinner();
    }

    public LinkedList<Player> getPlayers() {
        return playerList;
    }
}
