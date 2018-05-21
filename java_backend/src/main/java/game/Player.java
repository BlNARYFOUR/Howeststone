package game;

import cards.CardCollection;
import hero.Hero;

import java.util.List;

public class Player {
    private Hero hero;
    private CardCollection cardsInHand;
    private CardCollection cardsOnPlayingField;
    private CardCollection cardsInDeck;

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

    @Override
    public String toString() {
        // deck hero
        // mss nog extra zoals health mana
        return "Hero: " + hero.getHeroName() + "\nDeck: " +cardsInDeck.getNameOfCardCollection();
    }

    public void setDeck(String deckName) {
        cardsInDeck = new CardCollection(deckName);
    }

    public CardCollection getDeck() {
        return cardsInDeck;
    }

    public void setCardsInHand(CardCollection cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
}
