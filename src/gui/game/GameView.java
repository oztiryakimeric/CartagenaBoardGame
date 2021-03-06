package gui.game;

import model.IGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class GameView extends JPanel {
    private IGame game;

    private BoardView boardView;
    private GamePadView gamePadView;
    private CardListView cardListView;

    public GameView(IGame game) {
        this.game = game;
        this.setLayout(new BorderLayout());

        boardView = new BoardView(game);
        add(boardView, BorderLayout.CENTER);

        JPanel rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout());
        add(rightContainer, BorderLayout.EAST);

        gamePadView = new GamePadView(game.getCurrentPlayer());
        cardListView = new CardListView(game.getPlayerList());

        rightContainer.add(gamePadView, BorderLayout.NORTH);
        rightContainer.add(cardListView, BorderLayout.CENTER);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public GamePadView getGamePadView() {
        return gamePadView;
    }

    public CardListView getCardListView() {
        return cardListView;
    }


}
