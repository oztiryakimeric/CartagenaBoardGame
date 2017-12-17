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

    private Cell selectedCell;
    private String selectedAction;
    private Symbol selectedSymbol;

    public Controller(Gui gui, Game game) {
        this.gui = gui;
        this.game = game;

        gui.getBoardView().addCellSelectListener(new BoardViewController());
        gui.getPlayerView().addInteractionListener(new PlayerViewController());
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

    private class BoardViewController implements CellSelectListener{
        @Override
        public void cellSelected(Cell cell) {
            selectedCell = cell;

            gui.getBoardView().highlightCell(cell, Color.green);
            gui.getBoardView().setEnabled(false);

            gui.getPlayerView().pirateSelected(null);
            gui.getPlayerView().setEnabledActionRow(true);
        }
    }

    private class PlayerViewController implements PlayerViewInteraction{

        @Override
        public void actionSelected(String action) {
            selectedAction = action;
            if(action.equals("backward")){
                gui.getPlayerView().setEnabledActionRow(false);
                gui.getPlayerView().setEnabledPlayRow(true);
            }
            else if(action.equals("forward")){
                gui.getPlayerView().setEnabledActionRow(false);
                gui.getPlayerView().setEnabledCardRow(true);
            }
        }

        @Override
        public void cardSelected(Symbol symbol) {
            selectedSymbol = symbol;
            gui.getPlayerView().setEnabledCardRow(false);
            gui.getPlayerView().setEnabledPlayRow(true);
        }

        @Override
        public void playClicked() {
            //burada oynaması lazım pirate veya cell, action ve card felan ile
            //su an cell var pirate i falan bulmak lazım bi sekilde.........
            System.out.println("burada oynaması lazım pirate veya cell, action ve card falan ile...........");
            selectedCell = null;
            selectedAction = null;
            selectedSymbol = null;

            gui.getBoardView().removeHighlights();
            gui.getBoardView().setEnabled(true);

            gui.getPlayerView().initialStateWithSkip();
        }

        @Override
        public void skipClicked() {
            //burada game next oyuncuya geçmeli
            System.out.println("burada game next oyuncuya geçmeli");
        }
    }
}





