package gui.setup;

import gui.game.GameController;
import gui.game.GameView;
import model.GameBuilder;
import model.IGame;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oztiryakimeric on 13.01.2018.
 */
public class SetupController implements ActionListener{

    private SetupView gui;

    private List<Symbol> newSymbols;

    public SetupController(SetupView gui) {
        this.gui = gui;
        this.newSymbols = new ArrayList<>();
        gui.getAddSymbolButton().addActionListener(this);
        gui.getStartGameButton().addActionListener(this);
    }

    public void start(){
        JFrame frame = new JFrame("Cartagena");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 350);
        frame.setMinimumSize(new Dimension(200, 350));
        frame.setMaximumSize(new Dimension(200, 350));
        frame.setLayout(new BorderLayout());
        frame.add(gui, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(gui.getAddSymbolButton()))
            handleAddSymbolAction();
        else if (e.getSource().equals(gui.getStartGameButton()))
            handleStartGameAction();
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

    private void handleStartGameAction(){
        newSymbols.addAll(Symbol.getSymbols());

        IGame game = GameBuilder.buildSingle(2, 6, newSymbols);

        GameView gui = new GameView(game);

        GameController controller = new GameController(gui, game);
        controller.start();
    }
}
