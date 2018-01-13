package gui.setup;

import model.Symbol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 18.12.2017.
 */
public class SetupView extends JPanel{

    private JPanel container;
    private JComboBox<String> playerCountComboBox;
    private JSpinner segmentCountSpinner;
    private JComboBox<String> symbolListComboBox;
    private JButton addSymbolButton;
    private JButton singleButton;
    private JButton multiButton;
    private JLabel messageLabel;

    public SetupView()  {
        this.setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new GridLayout(5, 1));
        add(container, BorderLayout.NORTH);

        initializePlayerCountRow();
        initializeSegmentCountRow();
        initializeSymbolListRow();
        initializeStartGameRow();
        initializeMessageRow();
    }

    private void initializePlayerCountRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Player Count"));
        container.add(borderedPanel);

        playerCountComboBox = new JComboBox<>();
        for(int i=2; i<6; i++)
            playerCountComboBox.addItem(String.valueOf(i));

        borderedPanel.add(playerCountComboBox);
    }

    private void initializeSegmentCountRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Segment Count"));
        container.add(borderedPanel);

        SpinnerNumberModel model = new SpinnerNumberModel(6,1,20,1);
        segmentCountSpinner = new JSpinner(model);
        borderedPanel.add(segmentCountSpinner);
    }

    private void initializeSymbolListRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setLayout(new GridLayout(1,2));
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Symbol List"));
        container.add(borderedPanel);

        symbolListComboBox = new JComboBox<>();
        for(Symbol symbol: Symbol.getSymbols()){
            symbolListComboBox.addItem(symbol.toString());
        }

        addSymbolButton = new JButton("Add");

        borderedPanel.add(symbolListComboBox);
        borderedPanel.add(addSymbolButton);
    }

    private void initializeStartGameRow(){
        JPanel borderedPanel = new JPanel();
        borderedPanel.setLayout(new GridLayout(1,2));
        borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Start Game"));
        container.add(borderedPanel);

        singleButton = new JButton("Single");
        multiButton = new JButton("Multi");

        borderedPanel.add(singleButton);
        borderedPanel.add(multiButton);
    }

    private void initializeMessageRow(){
        messageLabel = new JLabel();
        container.add(messageLabel);
    }

    public JComboBox<String> getPlayerCountComboBox() {
        return playerCountComboBox;
    }

    public JSpinner getSegmentCountSpinner() {
        return segmentCountSpinner;
    }

    public JComboBox<String> getSymbolListComboBox() {
        return symbolListComboBox;
    }

    public JButton getAddSymbolButton() {
        return addSymbolButton;
    }

    public JButton getSingleButton() {
        return singleButton;
    }

    public JButton getMultiButton() {
        return multiButton;
    }

    public void setMessage(String message){
        messageLabel.setText(message);
    }
}




