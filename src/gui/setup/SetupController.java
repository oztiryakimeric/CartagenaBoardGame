package gui.setup;

import Server.GameServer;
import gui.game.GameController;
import gui.game.GameView;
import model.GameBuilder;
import model.IGame;
import model.MultiplayerGame;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public class SetupController implements ActionListener{

    private JFrame frame;
    private SetupView gui;

    private List<Symbol> newSymbols;

    public SetupController(SetupView gui) {
        this.gui = gui;
        this.newSymbols = new ArrayList<>();
        gui.getAddSymbolButton().addActionListener(this);
        gui.getSingleButton().addActionListener(this);
        gui.getMultiButton().addActionListener(this);
    }

    public void start(){
        frame = new JFrame("Cartagena");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setMinimumSize(new Dimension(350, 350));
        frame.setMaximumSize(new Dimension(350, 350));
        frame.setLayout(new BorderLayout());
        frame.add(gui, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(gui.getAddSymbolButton()))
            handleAddSymbolAction();
        else if (e.getSource().equals(gui.getSingleButton()))
            handleStartSingleGameAction();
        else if (e.getSource().equals(gui.getMultiButton()))
            handleStartMultiGameAction();
    }

    private void handleAddSymbolAction(){
        String enteredValue = (String)JOptionPane.showInputDialog(gui,"Enter symbol name please...",
                "Symbol Name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

        if (enteredValue != null && enteredValue.length() > 0) {
            newSymbols.add(new Symbol(enteredValue));
            gui.getSymbolListComboBox().addItem(enteredValue);
            return;
        }
    }

    private void handleStartSingleGameAction(){
        newSymbols.addAll(Symbol.getSymbols());
        int playerCount = Integer.parseInt((String) gui.getPlayerCountComboBox().getSelectedItem());
        int segmentCount = (int) gui.getSegmentCountSpinner().getValue();

        IGame game = GameBuilder.buildSingle(playerCount, segmentCount, newSymbols);

        GameView gui = new GameView(game);

        GameController controller = new GameController(gui, game);
        controller.start();
        frame.dispose();
    }

    private void handleStartMultiGameAction(){
        String[] buttons = {"Create Server", "Join Server"};
        int returnValue = JOptionPane.showOptionDialog(gui, "Select action", "Multiplayer",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);

        newSymbols.addAll(Symbol.getSymbols());
        int playerCount = Integer.parseInt((String) gui.getPlayerCountComboBox().getSelectedItem());
        int segmentCount = (int) gui.getSegmentCountSpinner().getValue();

        if(returnValue == 0){
            gui.setMessage("Server created. Wating other players.");
            new Thread(() -> {
                try {
                    new GameServer(playerCount, segmentCount, newSymbols);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            gui.getMultiButton().setEnabled(false);
            gui.getSingleButton().setEnabled(false);
            startMultiplayerGame(playerCount, segmentCount, newSymbols);
        }
        else if(returnValue == 1){
            gui.setMessage("Wating other players. Game will start soon.");
            gui.getMultiButton().setEnabled(false);
            gui.getSingleButton().setEnabled(false);
            startMultiplayerGame(playerCount, segmentCount, newSymbols);
        }
    }

    private void startMultiplayerGame(int playerCount, int segmentCount, List<Symbol> symbolList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                IGame game = GameBuilder.buildMulti();
                try {
                    game = new MultiplayerGame(game);
                    GameView gameView = new GameView(game);
                    GameController controller = new GameController(gameView, game);
                    game.setController(controller);
                    controller.start();
                    frame.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
