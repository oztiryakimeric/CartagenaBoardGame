package model;

import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public interface IGame {
    void playForward(Pirate pirate, Symbol symbol);
    void playBackward(Pirate pirate);

    Player getCurrentPlayer();
    void switchToNextPlayer();

    boolean isFinished();


    Board getBoard();
    List<Player> getPlayerList();
}
