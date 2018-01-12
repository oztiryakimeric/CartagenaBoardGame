package model;

import java.util.List;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class MultiplayerGame implements IGame {

    private IGame game;

    public MultiplayerGame(IGame game) {
        this.game = game;
    }

    @Override
    public void playForward(Pirate pirate, Symbol symbol) {
        game.playForward(pirate, symbol);
        System.out.println("Send play forward command to server.");
    }

    @Override
    public void playBackward(Pirate pirate) {
        game.playBackward(pirate);
        System.out.println("Send play backward command to server.");
    }

    @Override
    public void switchToNextPlayer() {
        game.switchToNextPlayer();
        System.out.println("Send switch next player command to server.");
    }

    @Override
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    @Override
    public boolean isFinished() {
        return game.isFinished();
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }

    @Override
    public List<Player> getPlayerList() {
        return game.getPlayerList();
    }
}
