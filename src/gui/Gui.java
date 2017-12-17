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

    public Gui(Game game) {
        this.game = game;
        this.setLayout(new BorderLayout());

        boardView = new BoardView(game);
        add(boardView, BorderLayout.CENTER);

        PlayerView playerView = new PlayerView(game.getPlayerList().get(0));
        add(playerView, BorderLayout.EAST);

        boardView.addCellSelectListener(new CellSelectListener() {
            @Override
            public void cellSelected(Cell cell) {
                System.out.println(cell.toString());
                boardView.highlightCell(cell, Color.green);

            }
        });

        Cell cell = game.getBoard().getSegments()[0].getCells()[2];
        game.getPlayerList().get(0).getPirateList().get(0).setCell(cell);
        boardView.repaint();

    }
}
