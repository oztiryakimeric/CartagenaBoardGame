package gui;

import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Gui extends JPanel {
    private Game game;

    private BoardView boardView;

    public Gui(Game game) {
        this.game = game;
        this.setLayout(new BorderLayout());

        boardView = new BoardView(game.getBoard());
        add(boardView);
    }
}
