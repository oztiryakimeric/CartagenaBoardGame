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

        boardView = new BoardView(game);
        add(boardView, BorderLayout.CENTER);

        JPanel playerPanel = new JPanel();

        PlayerView p1 = new PlayerView(game.getPlayerList().get(0));
        PlayerView p2 = new PlayerView(game.getPlayerList().get(1));

        playerPanel.setLayout(new GridLayout(1, 2));
        playerPanel.add(p1);
        playerPanel.add(p2);

        add(playerPanel, BorderLayout.SOUTH);
    }
}
