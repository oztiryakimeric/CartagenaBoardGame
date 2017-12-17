package gui;

import model.Cell;
import model.Game;
import model.GameCell;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Gui extends JPanel {
    private Game game;

    private NewBoardView boardView;

    public Gui(Game game) {
        this.game = game;
        this.setLayout(new BorderLayout());

        boardView = new NewBoardView(game);
        add(boardView, BorderLayout.CENTER);

        boardView.addCellSelectListener(new CellSelectListener() {
            @Override
            public void cellSelected(GameCell cell) {
                System.out.println(cell.toString());
                boardView.highlightCell(cell, Color.green);
            }
        });

        Cell cell = game.getBoard().getSegments()[0].getCells()[2];
        game.getPlayerList().get(0).getPirateList().get(0).setCell(cell);
        boardView.repaint();

    }
}
