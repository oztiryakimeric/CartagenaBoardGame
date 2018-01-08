package gui;

import model.Cell;
import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Gui extends JPanel {
    private Game game;

    private BoardView boardView;
    private PlayerView playerView;

    public Gui(Game game) {
        this.game = game;
        this.setLayout(new BorderLayout());

        boardView = new BoardView(game);
        add(boardView, BorderLayout.CENTER);

        playerView = new PlayerView(game.getCurrentPlayer());
        add(playerView, BorderLayout.EAST);
    }

    public Game getGame() {
        return game;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public void finish(){
        int output = JOptionPane.showConfirmDialog(null, "The Winner is Player " + (game.getCurrentPlayer().getId() + 1) + ".", "CONGRATULAIONS!!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);

        if(output == JOptionPane.OK_OPTION)
            System.exit(0);

    }
}
