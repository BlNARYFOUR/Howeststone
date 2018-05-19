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
    private Player you;
    private Player enemy;
    private int turnTime;
    /*private Hero you;
    private CardCollection deck;*/

    public void setHero(String hero) {
        you = new Player();
        you.setHero(new Hero(hero));
    }

    public Hero getHero() {
        return you.getHero();
    }

    public void setEnemy(String hero) {
        enemy = new Player();
        enemy.setHero(new Hero(hero));
        enemy.setDeck("Standard");
    }

    public Hero getEnemy() {
        return enemy.getHero();
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
        you.setDeck(deckName);
    }

    public Player getYou() {
        return you;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }
}