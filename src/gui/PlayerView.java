package gui;

import model.Player;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by oztiryakimeric on 13.12.2017.
 */
public class PlayerView extends JPanel {
    private Player player;

    private JPanel container;

    private JLabel selectPirateLabel;
    private JRadioButton backwordRadioButton;
    private JRadioButton forwardRadioButton;
    private JComboBox<Symbol> deckComboBox;


    public PlayerView(Player player) {
        this.player = player;
        this.setBackground(player.getColor());
        this.setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new GridLayout(5, 1));

        initializeSelectPirateRow();
        initializeChooseActionRow();
        initializeChooseCardRow();

        this.add(container, BorderLayout.NORTH);
    }

    private void initializeSelectPirateRow(){
        JPanel piratePanel = new JPanel();
        piratePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Select Pirate"));

        selectPirateLabel = new JLabel("Click the cell that you want to select.");
        piratePanel.add(selectPirateLabel);

        container.add(piratePanel);
    }

    private void initializeChooseActionRow(){
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 2));
        actionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose Action"));

        backwordRadioButton = new JRadioButton("Backward");
        forwardRadioButton = new JRadioButton("Forward");

        ButtonGroup group = new ButtonGroup();
        group.add(backwordRadioButton);
        group.add(forwardRadioButton);

        actionPanel.add(backwordRadioButton);
        actionPanel.add(forwardRadioButton);

        container.add(actionPanel);
    }

    private void initializeChooseCardRow(){
        JPanel symbolPanel = new JPanel();
        symbolPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose Card"));

        deckComboBox = new JComboBox<>();

        for(Symbol s: player.getDeck())
            deckComboBox.addItem(s);

        symbolPanel.add(deckComboBox);

        container.add(symbolPanel);
    }
}
