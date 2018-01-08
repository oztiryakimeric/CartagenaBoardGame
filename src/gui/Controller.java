package gui;

import model.*;

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
    private Pirate selectedPirate;
    int turnCounter;

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

        turnCounter = 0;
    }

    private class BoardViewController implements CellSelectListener{
        @Override
        public void cellSelected(Cell cell) {
            selectedCell = cell;
            selectedPirate = null;

            if(selectedCell != null){
                gui.getBoardView().highlightCell(cell, Color.green);
                gui.getBoardView().setEnabled(true);
            }

            for(int i = 0; i < game.getCurrentPlayer().getPirateList().size(); i++) {
                if (game.getCurrentPlayer().getPirateList().get(i).getCell().equals(cell)){
                    selectedPirate = game.getCurrentPlayer().getPirateList().get(i);
                    break;
                }
            }
            
            if(selectedCell == BoatCell.getInstance()){
                JOptionPane.showConfirmDialog(null, "Your pirates are drinking rum in their boat. Don't disturb them to move somewhere else...", "Wrong Cell", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                selectedCell = null;
                gui.getPlayerView().setEnabledActionRow(false);
            } else if(selectedCell == BeginCell.getInstance()){
                gui.getPlayerView().setEnabledCardRow(true);
                gui.getPlayerView().setEnabledActionRow(false);
                selectedAction = "forward";
            } else if(selectedCell == null){
                JOptionPane.showConfirmDialog(null, "Don't try to push me to throwing NullPointer again. There is no way to go there as you can see.", "There is no cell", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                gui.getPlayerView().setEnabledActionRow(false);
            } else {
                if(selectedPirate == null){
                    JOptionPane.showConfirmDialog(null, "You have no pirate here select again.", "Wrong Cell", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                    gui.getBoardView().removeHighlights();
                    selectedCell = null;
                }
                else {
                    gui.getPlayerView().pirateSelected(selectedPirate);
                    gui.getPlayerView().setEnabledActionRow(true);
                }
            }
        }
    }

    private class PlayerViewController implements PlayerViewInteraction{

        @Override
        public void actionSelected(String action) {
            selectedAction = action;
            if(action.equals("backward")){
                gui.getPlayerView().setEnabledActionRow(true);
                gui.getPlayerView().setEnabledPlayRow(true);
                gui.getPlayerView().setEnabledCardRow(false);
            }
            else if(action.equals("forward")){
                gui.getPlayerView().setEnabledActionRow(true);
                gui.getPlayerView().setEnabledCardRow(true);
            }
        }

        @Override
        public void cardSelected(Symbol symbol) {
            selectedSymbol = symbol;
            gui.getPlayerView().setEnabledCardRow(true);
            gui.getPlayerView().setEnabledPlayRow(true);
        }

        @Override
        public void playClicked() {
            game.move(selectedPirate, selectedSymbol, selectedAction);
            gui.getBoardView().getPirateDrawer().drawPlayers();

            gui.getBoardView().removeHighlights();
            gui.getBoardView().setEnabled(true);

            gui.getPlayerView().initialStateWithSkip();
            gui.getPlayerView().setEnabledActionRow(false);

            turnCounter++;

            if(turnCounter > 1){
                game.switchToNextPlayer();
                turnCounter = 0;
                gui.getPlayerView().initialState();
            }

            updateViews();

            if(game.isFinished()){
                gui.finish();
            }
        }

        @Override
        public void skipClicked() {
            game.switchToNextPlayer();
            turnCounter = 0;
            gui.getPlayerView().initialState();
            updateViews();
        }

        private void updateViews(){
            gui.getPlayerView().setCurrentPlayer(game.getCurrentPlayer());
            gui.getPlayerView().updateDeckComboBox();
            gui.getPlayerView().updateTurn();
        }
    }
}





