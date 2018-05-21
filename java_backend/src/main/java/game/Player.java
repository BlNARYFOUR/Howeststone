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

    public void setHero(String heroName){
        this.hero = new Hero(heroName);
    }

    public Player() {

    }

    void beginTurn() {
        // +1 mana until 10
        // draw card
    }

    public void setDeck(String deckName) {
        cardsInDeck = new CardCollection(deckName);
    }

    public CardCollection getDeck() {
        return cardsInDeck;
    }
}
