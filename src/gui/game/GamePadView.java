package gui.game;

import gui.Util;
import model.Player;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by oztiryakimeric on 13.12.2017.
 */
public class GamePadView extends JPanel {
    private static final String PIRATE_SELECTED_MESSAGE = "Pirate Selected.";
    private static final String SELECT_PIRATE_MESSAGE = "Click the cell that you want to select.";

    private Player player;

    private JPanel container;
    private PlayerViewInteraction listener;

    private JLabel selectPirateLabel;
    private JRadioButton backwardRadioButton;
    private JRadioButton forwardRadioButton;
    private DeckComboBox deckComboBox;
    private JButton playButton;
    private JButton skipButton;
    private JLabel turnHolderLabel = new JLabel();
    ButtonGroup group;

    public GamePadView(Player player) {
        this.player = player;

        this.setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Play Screen"));
        setBackground(Color.orange);

        container = new JPanel();
        container.setLayout(new GridLayout(5, 1));
        container.setBackground(Util.idToColor(player.getId()));

        initializeSelectPirateRow();
        initializeChooseActionRow();
        initializeChooseCardRow();
        initializePlayRow();
        initializeTurnLabel();
        initialState();

        this.add(container, BorderLayout.NORTH);
    }

    public void addInteractionListener(PlayerViewInteraction listener){
        this.listener = listener;
    }

    private void initializeSelectPirateRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Select Pirate"));
        borderedPanel.setOpaque(false);
        container.add(borderedPanel);

        selectPirateLabel = new JLabel();
        selectPirateLabel.setFont(new Font("Courier", Font.BOLD,14));
        selectPirateLabel.setPreferredSize(new Dimension(330, 20));
        borderedPanel.add(selectPirateLabel);
    }

    private void initializeChooseActionRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setLayout(new GridLayout(1, 2));
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose Action"));
        borderedPanel.setOpaque(false);
        container.add(borderedPanel);

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

        group = new ButtonGroup();
        group.add(backwardRadioButton);
        group.add(forwardRadioButton);

        borderedPanel.add(backwardRadioButton);
        borderedPanel.add(forwardRadioButton);
    }

    private void initializeChooseCardRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Choose Card"));
        borderedPanel.setOpaque(false);
        container.add(borderedPanel);

        deckComboBox = new DeckComboBox(player.getDeck());
        deckComboBox.addCardSelectedListener(symbol -> {
            if(listener != null)
                listener.cardSelected(symbol);
        });

        borderedPanel.add(deckComboBox);
    }

    public void updateDeckComboBox(){
        deckComboBox.update();
        group.clearSelection();
    }

    private void initializePlayRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setLayout(new GridLayout(1,2));
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Play"));
        borderedPanel.setOpaque(false);
        container.add(borderedPanel);

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

        borderedPanel.add(playButton);
        borderedPanel.add(skipButton);
    }

    private void initializeTurnLabel(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Turn"));
        borderedPanel.setOpaque(false);
        container.add(borderedPanel);

        turnHolderLabel.setText("Player " + (player.getId() + 1) + "'s turn");
        turnHolderLabel.setFont(new Font("Courier", Font.BOLD,24));
        borderedPanel.add(turnHolderLabel);
    }

    public void initialState(){
        selectPirateLabel.setText(SELECT_PIRATE_MESSAGE);
        selectPirateLabel.setForeground(Color.black);
        enableDirectionRow(false);
        enableCardRow(false);
        enablePlayRow(false);
    }

    public void initialStateWithSkip(){
        initialState();
        enablePlayRow(true);
        playButton.setEnabled(false);
        skipButton.setEnabled(true);
    }

    public void enableDirectionRow(boolean b){
        backwardRadioButton.setEnabled(b);
        forwardRadioButton.setEnabled(b);
    }

    public void setDirectionToForward(){
        forwardRadioButton.setSelected(true);
    }

    public void enableCardRow(boolean b){
        deckComboBox.setEnabled(b);
    }

    public void enablePlayRow(boolean b){
        playButton.setEnabled(b);
        skipButton.setEnabled(false);
    }

    public void disableCompletly(){
        Dimension size = getPreferredSize();
        this.container.setVisible(false);
        this.setPreferredSize(size);
    }

    public void enableCompletly(){
        this.container.setVisible(true);
    }

    public void pirateSelected(){
        selectPirateLabel.setText(PIRATE_SELECTED_MESSAGE);
    }

    public void update(Player player){
        this.player = player;
        container.setBackground(Util.idToColor(player.getId()));
        this.deckComboBox.setDeck(player.getDeck());
        updateDeckComboBox();
        turnHolderLabel.setText("Player " + (player.getId() + 1) + "'s turn");
    }
    public interface PlayerViewInteraction{
        void actionSelected(String action);
        void cardSelected(Symbol symbol);
        void playClicked();
        void skipClicked();
    }
}












