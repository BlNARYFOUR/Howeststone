package game;

import cards.CardCollection;
import hero.Hero;
import sun.security.krb5.internal.crypto.DesMacCksumType;

import java.util.ArrayList;
import java.util.Collections;
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


    public Hero getHero() {
        return you.getHero();
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

    @Override
    public String toString() {

        return "\nPlayer: " + you.getHero() + "\n     VS" + "\nEnemy: " + enemy.getHero();
    }

    public Player getYou() {
        return you;
    }

    public void addPlayer(Player you) {
        this.you = you;
    }

    public void addBot(Player enemy) {
        this.enemy = enemy;
    }

    public void setTime(int time) {
        this.turnTime = time;
    }

    public void shuffleDecks() {

    }
}