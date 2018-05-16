package game;

import cards.CardCollection;
import hero.Hero;

public class Player {
    Hero ownHero;
    CardCollection cardsInHand;
    CardCollection cardsOnPlayingField;
    CardCollection cardsInDeck;

    public Player(Hero ownHero) {
        this.ownHero = ownHero;
        cardsOnPlayingField = new CardCollection();
        cardsInHand = new CardCollection();
        cardsInDeck = new CardCollection();
    }

    void beginTurn() {
        // +1 mana until 10
        // draw card
    }
}
