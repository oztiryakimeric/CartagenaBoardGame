package gui;

import model.Cell;
import model.Game;
import model.Symbol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Controller {
    private Gui gui;
    private Game game;

    public Controller(Gui gui, Game game) {
        this.gui = gui;
        this.game = game;

        gui.getBoardView().addCellSelectListener(new CellSelectListener() {
            @Override
            public void cellSelected(Cell cell) {
                gui.getBoardView().highlightCell(cell, Color.green);
                gui.getBoardView().setEnabled(false);

                gui.getPlayerView().pirateSelected(null);
                gui.getPlayerView().setEnabledActionRow(true);

            }
        });

        gui.getPlayerView().addInteractionListener(new PlayerViewInteraction() {
            @Override
            public void actionSelected(String action) {
                gui.getPlayerView().setEnabledCardRow(true);
            }

            @Override
            public void cardSelected(Symbol symbol) {
                System.out.println("Player choose card" + symbol.toString());
                gui.getPlayerView().setEnabledPlayRow(true);
            }
        });
    }

    public void start(){
        JFrame frame = new JFrame("Cartagena");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(1000, 800));
        frame.setLayout(new BorderLayout());
        frame.add(gui, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
