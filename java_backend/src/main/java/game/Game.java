package game;

import cards.CardCollection;
import hero.Hero;
import sun.security.krb5.internal.crypto.DesMacCksumType;

import java.util.ArrayList;
import java.util.List;

public class Game {
    // TODO: MOCKERS
    protected static final String[] MOCKED_HEROES = {"Mage", "Paladin"};

    // TODO: actual fields
    private Hero you;
    private CardCollection deck;

    public void setHero(String hero) {
        this.you = new Hero(hero);
    }

    public Hero getHero() {
        return you;
    }

    // TODO change to multiple Heroes
    public String[] getHeroNames() {
        return MOCKED_HEROES;
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

    public List<String> getDeckNames() {
        List<String> mocked = new ArrayList<String>();

        mocked.add("Standard");
        mocked.add("Deck 1");
        mocked.add("Deck 2");

        return mocked;
    }
}