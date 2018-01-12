package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by oztiryakimeric on 18.12.2017.
 */
public class SetupView extends JPanel {

    private JComboBox<Integer> playerCountComboBox;
    private JButton startButton;

    private StartButtonListener listener;

    public SetupView(){
        initializePlayerCountComboBox();
        initializeStartButton();
    }

    public void addStartButtonListener(StartButtonListener listener){
        this.listener = listener;
    }

    private void initializePlayerCountComboBox(){
        playerCountComboBox = new JComboBox<>();

        for(int i=2; i<=5; i++){
            playerCountComboBox.addItem(i);
        }
    }

    private void initializeStartButton(){
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null)
                    listener.startButtonClicked((int) playerCountComboBox.getSelectedItem());
            }
        });
    }

    public interface StartButtonListener{
        void startButtonClicked(int playerCount);
    }
}




