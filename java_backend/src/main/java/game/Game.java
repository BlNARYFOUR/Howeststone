package game;

import cards.CardCollection;
import hero.Hero;
import sun.security.krb5.internal.crypto.DesMacCksumType;

public class Game {
    private Hero you;
    private CardCollection deck;

    public void setHero(String hero) {
        this.you = new Hero(hero);
    }

    public Hero getHero() {
        return you;
    }

    // TODO change to multiple Heroes
    public String getHeroes() {
        return "Mage Paladin";
    }

    // TODO change to multiple Decks
    public String getDecks() {
        return "Standard";
    }

    public void setDeck(String deckName) {
        // TODO check if deck exist
        if (deckName.equals("Standard")) {
            this.deck = new CardCollection(deckName);
        }
    }

    public CardCollection getDeck() {
        return deck;
    }
}