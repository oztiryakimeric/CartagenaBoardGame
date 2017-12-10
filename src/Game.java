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
            turnCount++;
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
        currentPlayer = playerList.get(turnCount % numPlayers);
    }

    public boolean isFinished() {
        return getWinner() != null;
    }

    //yeni değişimle beraber getWinner methodu her turun sonunda oynayan oyuncu için kontrol yapacak.
    public Player getWinner() {
        if(currentPlayer().getPawnInBoat() == 6){
            return currentPlayer();
        }
        return null;
    }

    public LinkedList<Player> getPlayers() {
        return playerList;
    }
}
