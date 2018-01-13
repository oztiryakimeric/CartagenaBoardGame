package Server;

import model.Deck;
import model.IGame;

public class SetDeckCommand implements Command{
    private Deck deck;

    public SetDeckCommand(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void executeOn(IGame game) {
        game.setDeck(deck);
    }
}
