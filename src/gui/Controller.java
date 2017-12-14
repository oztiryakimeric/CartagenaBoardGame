package gui;

import model.Game;

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
    }

    public void start(){
        JFrame frame = new JFrame("Cartagena");
        frame.setSize(900, 800);
        frame.setLayout(new BorderLayout());
        frame.add(gui, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
