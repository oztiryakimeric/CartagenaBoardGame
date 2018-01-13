package gui.game;

import gui.Util;
import model.Player;
import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by oztiryakimeric on 12.01.2018.
 */
public class CardListView extends JPanel{

    private List<Player> playerList;
    private JPanel container;

    public CardListView(List<Player> playerList) {
        this.playerList = playerList;
        this.setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Card List"));
        setBackground(Color.orange);

        container = new JPanel();
        container.setLayout(new GridLayout(playerList.size(), 1));
        JScrollPane scrollPane = new JScrollPane(container);
        add(scrollPane, BorderLayout.CENTER);

        populate();
    }

    private void populate(){
        for(Player player: playerList){
            JPanel borderedPanel = new JPanel();
            borderedPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), player.getId() + 1 + ". Player's cards"));
            borderedPanel.setBackground(Util.idToColor(player.getId()));

            HashMap<Symbol, Integer> cardMap = new HashMap<>();

            for(Symbol symbol: player.getDeck()){
                int count = cardMap.getOrDefault(symbol, 0);
                cardMap.put(symbol, count+1);
            }

            borderedPanel.setLayout(new GridLayout(cardMap.entrySet().size(), 1));
            Iterator it = cardMap.entrySet().iterator();

            boolean empty = true;
            while(it.hasNext()){
                empty = false;
                Map.Entry pair = (Map.Entry)it.next();
                Symbol symbol = (Symbol) pair.getKey();
                int count = (int) pair.getValue();
                borderedPanel.add(new Label(symbol + "(" + count + ")"));
                it.remove();
            }
            if(empty)
                borderedPanel.add(new Label("He don't have any card"));

            container.add(borderedPanel);
        }
    }

    public void update(){
        container.removeAll();
        populate();
    }
}
