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
        turnCounter = 0;

        gui.getBoardView().addCellSelectListener(new BoardViewController());
        gui.getGamePadView().addInteractionListener(new PlayerViewController());
    }

    public void start(){
        JFrame frame = new JFrame("Cartagena");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 800);
        frame.setMinimumSize(new Dimension(1000, 800));
        frame.setLayout(new BorderLayout());
        frame.add(gui, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showMessage(String title, String message){
        JOptionPane.showConfirmDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }

    private class BoardViewController implements BoardView.CellSelectListener {
        @Override
        public void cellSelected(Cell cell) {
            selectedCell = cell;
            selectedPirate = game.getCurrentPlayer().getPirateAt(cell);

            gui.getBoardView().removeHighlights();
            gui.getBoardView().highlightCell(cell, Color.gray);

            if(selectedPirate == null){
                showMessage("Wrong Cell", "You have no pirate here select again.");
                gui.getBoardView().removeHighlights();
                gui.getBoardView().setEnabled(true);
                return;
            }
            else if(selectedCell instanceof BeginCell){
                gui.getGamePadView().enableCardRow(true);
                gui.getGamePadView().enableDirectionRow(false);
                gui.getGamePadView().setDirectionToForward();
                selectedAction = "forward";
            }
            else {
                gui.getGamePadView().pirateSelected();
                gui.getGamePadView().enableDirectionRow(true);
            }
        }
    }

    private class PlayerViewController implements GamePadView.PlayerViewInteraction {

        private Cell highlightedFutureLocation;

        @Override
        public void actionSelected(String action) {
            selectedAction = action;
            if(action.equals("backward")){
                Cell possibleCell = game.getBoard().findPossibleBackwardCell(selectedPirate);
                if(possibleCell.equals(selectedCell)){
                    showMessage("Oops", "You can't move that pirate. That is not valid.");
                }
                else{
                    if(highlightedFutureLocation != null)
                        gui.getBoardView().removeHighlight(highlightedFutureLocation);
                    highlightedFutureLocation = possibleCell;
                    gui.getBoardView().highlightCell(possibleCell, new Color(0, 200, 0, 100));
                    gui.getBoardView().setEnabled(true);
                    gui.getGamePadView().enablePlayRow(true);
                    gui.getGamePadView().enableCardRow(false);
                }
            }
            else if(action.equals("forward")){
                gui.getGamePadView().enableCardRow(true);
            }
        }

        @Override
        public void cardSelected(Symbol symbol) {
            selectedSymbol = symbol;

            Cell possibleCell = game.getBoard().findPossibleForwardCell(selectedPirate, symbol);

            if(highlightedFutureLocation != null)
                gui.getBoardView().removeHighlight(highlightedFutureLocation);
            highlightedFutureLocation = possibleCell;
            gui.getBoardView().highlightCell(possibleCell, new Color(0, 200, 0, 100));
            gui.getGamePadView().enableDirectionRow(false);
            gui.getGamePadView().enablePlayRow(true);
        }

        @Override
        public void playClicked() {
            if(selectedAction.equals("forward"))
                game.playForward(selectedPirate, selectedSymbol);
            else if(selectedAction.equals("backward"))
                game.playBackward(selectedPirate);

            gui.getBoardView().repaint();

            gui.getBoardView().removeHighlights();
            gui.getBoardView().setEnabled(true);

            gui.getGamePadView().initialStateWithSkip();
            gui.getGamePadView().enableDirectionRow(false);

            if(++turnCounter > 1){
                game.switchToNextPlayer();
                turnCounter = 0;
                update();
                gui.getGamePadView().initialState();
            }
            else{
                update();
                gui.getGamePadView().initialStateWithSkip();
            }

            if(game.isFinished()){
                gui.finish();
            }
        }

        @Override
        public void skipClicked() {
            game.switchToNextPlayer();
            turnCounter = 0;
            update();
            gui.getGamePadView().initialState();
        }

        private void update(){
            gui.getGamePadView().update(game.getCurrentPlayer());


            gui.getBoardView().removeHighlights();
            gui.getBoardView().setEnabled(true);
            gui.getCardListView().update();
        }
    }
}





