package game;

import hero.Hero;

public class Game {
    // TODO: MOCKERS
    protected static final String[] MOCKED_HEROES = {"Mage", "Paladin"};

    // TODO: actual fields
    private Player you;
    private Player enemy;
    private int turnTime;
    private String activePlayer;
    /*private Hero you;
    private CardCollection deck;*/


    public Hero getYourHero() {
        return you.getHero();
    }

    public Hero getEnemyHero() {
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

    public void setYourDeck(String deckName) {
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

    public Player getEnemy(){
        return enemy;
    }
    public void addYou(Player you) {
        this.you = you;
    }

    public void addEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public void setTime(int time) {
        this.turnTime = time;
    }

    public void shuffleDecks() {

    }

    public void setActivePlayer(String activePlayer) {
        this.activePlayer = activePlayer;
    }

    public String getActivePlayer() {
        return activePlayer;
    }
}