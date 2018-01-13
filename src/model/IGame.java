package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public interface IGame extends Serializable {
    void setBoard(Board board);
    void setPlayerList(List<Player> playerList);
    void setDeck(Deck deck);

    void playForward(Pirate pirate, Symbol symbol);
    void playBackward(Pirate pirate);

    Player getCurrentPlayer();
    void switchToNextPlayer();

    boolean isFinished();


    Board getBoard();
    List<Player> getPlayerList();
}
