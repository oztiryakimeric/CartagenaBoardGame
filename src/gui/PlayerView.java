package gui;

import model.Player;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by oztiryakimeric on 13.12.2017.
 */
public class PlayerView extends JPanel {
    private Player player;
    private ArrayList<JButton> symbolButtons;

    public PlayerView(Player player) {
        this.player = player;
        this.symbolButtons = new ArrayList<>();

        this.setBackground(player.getColor());
        this.setLayout(new GridLayout(1, player.getDeck().size()));

        for(Symbol symbol: player.getDeck()){
            JButton button = new JButton(symbol.toString());
            this.add(button);
            symbolButtons.add(button);
        }
    }
}
