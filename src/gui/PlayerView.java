package gui;

import model.Pirate;
import model.Player;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by oztiryakimeric on 13.12.2017.
 */
public class PlayerView extends JPanel {
    private Player player;

    private JPanel container;
    private PlayerViewInteraction listener;

    private JLabel selectPirateLabel;
    private JRadioButton backwardRadioButton;
    private JRadioButton forwardRadioButton;
    private JComboBox<DeckItem> deckComboBox;
    private JButton playButton;
    private JButton skipButton;

    public PlayerView(Player player) {
        this.player = player;
        this.setBackground(player.getColor());
        this.setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new GridLayout(4, 1));

        initializeSelectPirateRow();
        initializeChooseActionRow();
        initializeChooseCardRow();
        initializePlayRow();

        initialState();

        this.add(container, BorderLayout.NORTH);
    }

    public void addInteractionListener(PlayerViewInteraction listener){
        this.listener = listener;
    }

    private void initializeSelectPirateRow(){
        JPanel piratePanel = new JPanel();
        piratePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Select Pirate"));

        selectPirateLabel = new JLabel();
        piratePanel.add(selectPirateLabel);

        container.add(piratePanel);
    }

    private void initializeChooseActionRow(){
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 2));
        actionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose Action"));

        backwardRadioButton = new JRadioButton("Backward");
        forwardRadioButton = new JRadioButton("Forward");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null && forwardRadioButton.isSelected())
                    listener.actionSelected("forward");
                else
                    listener.actionSelected("backward");
            }
        };

        backwardRadioButton.addActionListener(actionListener);
        forwardRadioButton.addActionListener(actionListener);

        ButtonGroup group = new ButtonGroup();
        group.add(backwardRadioButton);
        group.add(forwardRadioButton);

        actionPanel.add(backwardRadioButton);
        actionPanel.add(forwardRadioButton);

        container.add(actionPanel);
    }

    private void initializeChooseCardRow(){
        JPanel symbolPanel = new JPanel();
        symbolPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose Card"));

        deckComboBox = new JComboBox<>();
        deckComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener!= null)
                    listener.cardSelected(((DeckItem) deckComboBox.getSelectedItem()).getSymbol());
            }
        });


        populateDeckComboBox();

        symbolPanel.add(deckComboBox);

        container.add(symbolPanel);
    }

    private void populateDeckComboBox(){
        HashMap<Symbol, Integer> cardMap = new HashMap<>();

        for(Symbol symbol: player.getDeck()){
            int count = cardMap.getOrDefault(symbol, 0);
            cardMap.put(symbol, count+1);
        }

        Iterator it = cardMap.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Symbol symbol = (Symbol) pair.getKey();
            int count = (int) pair.getValue();
            deckComboBox.addItem(new DeckItem(symbol, count));
            it.remove();
        }
    }

    private void initializePlayRow(){
        JPanel playPanel = new JPanel();
        playPanel.setLayout(new GridLayout(1,2));
        playPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Play"));

        playButton = new JButton("Play");
        skipButton = new JButton("Skip");

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(playButton) && listener != null){
                    listener.playClicked();
                }
                else if(e.getSource().equals(skipButton) && listener != null){
                    listener.skipClicked();
                }
            }
        };

        playButton.addActionListener(actionListener);
        skipButton.addActionListener(actionListener);

        playPanel.add(playButton);
        playPanel.add(skipButton);

        container.add(playPanel);
    }

    public void initialState(){
        selectPirateLabel.setText("Click the cell that you want to select.");
        selectPirateLabel.setForeground(Color.black);
        setEnabledActionRow(false);
        setEnabledCardRow(false);
        setEnabledPlayRow(false);
    }

    public void initialStateWithSkip(){
        initialState();
        setEnabledPlayRow(true);
        playButton.setEnabled(false);
        skipButton.setEnabled(true);
    }

    public void setEnabledActionRow(boolean b){
        backwardRadioButton.setEnabled(b);
        forwardRadioButton.setEnabled(b);
    }

    public void setEnabledCardRow(boolean b){
        deckComboBox.setEnabled(b);
    }

    public void setEnabledPlayRow(boolean b){
        playButton.setEnabled(b);
        skipButton.setEnabled(false);
    }

    public void pirateSelected(Pirate pirate){
        selectPirateLabel.setText("Pirate selected");
        selectPirateLabel.setForeground(Color.green);
    }
}

class DeckItem{
    private Symbol symbol;
    private int count;

    public DeckItem(Symbol symbol, int count) {
        this.symbol = symbol;
        this.count = count;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return symbol.toString() + " (" + count + ")";
    }
}

interface PlayerViewInteraction{
    void actionSelected(String action);
    void cardSelected(Symbol symbol);
    void playClicked();
    void skipClicked();
}










