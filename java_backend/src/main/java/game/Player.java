package game;

import cards.CardCollection;
import hero.Hero;

public class Player {
    private Hero hero;
    CardCollection cardsInHand;
    CardCollection cardsOnPlayingField;
    CardCollection cardsInDeck;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Player() {

    }

    void beginTurn() {
        // +1 mana until 10
        // draw card
    }

    public CardCollection setDeck(String deckName) {
        return new CardCollection(deckName);
    }

    public Object getDeck() {
        return cardsInDeck;
    }
}
