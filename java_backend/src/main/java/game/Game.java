package game;

import cards.CardCollection;
import hero.Hero;

import java.util.*;
public class Game {
    public List<String> heroNames = new ArrayList<>();
    public Map<String , CardCollection> deckNames = new HashMap<>();
    // TODO: actual fields
    private Player you;
    private Player enemy;
    private int manaYou;
    private int manaEnemy;
    private int turnTime;
    private String activePlayer;
    /*private Hero you;
    private CardCollection deck;*/

    public void generateEnemy(){
        final List<String> HEROES = new ArrayList<>(Arrays.asList(this.getHeroNames()));
        int randomHeroIndex = (int)Math.round(Math.random())*(HEROES.size()-1);
        Player enemy = new Player();
        enemy.setHero(HEROES.get(randomHeroIndex));
        enemy.setDeck("Standard");
        this.addEnemy(enemy);
    }

    public Hero getYourHero() {
        return you.getHero();
    }

    public Hero getEnemyHero() {
        return enemy.getHero();
    }

    // TODO change to multiple Heroes
    public List<String> getHeroNames() {
        return heroNames;
    }

    // TODO change to multiple Decks
    public String getDecks() {
        return "Standard";
    }

    public void setYourDeck(String deckName) {
        // TODO check if deck exist
        you.setDeck(deckName);
    }

    public int getManaYou() {
        return manaYou;
    }

    public void setManaYou(int manaYou) {
        this.manaYou = manaYou;
    }

    public int getManaEnemy() {
        return manaEnemy;
    }

    public void setManaEnemy(int manaEnemy) {
        this.manaEnemy = manaEnemy;
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

    public void shuffleDecks() {

    }

    public void setActivePlayer(String activePlayer) {
        this.activePlayer = activePlayer;
    }

    public String getActivePlayer() {
        return activePlayer;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }

    public List<String> getDeckNames() {
        List<String> deckNamesForChosenHero = new ArrayList<>();
        System.out.println(you.getHero().getHeroName());
        System.out.println(deckNames.get(you.getHero().getHeroName()));
        deckNamesForChosenHero.add(deckNames.get(you.getHero().getHeroName()).getNameOfCardCollection());
        return deckNamesForChosenHero;
    }
}