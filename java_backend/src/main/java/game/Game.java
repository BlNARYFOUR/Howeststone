package game;

import cards.Card;
import cards.CardCollection;
import cards.manaCardCollectionComparator;
import hero.Hero;

import java.util.*;

public class Game {
    // TODO: MOCKERS
    protected static final String[] MOCKED_HEROES = {"mage", "paladin"};

    // TODO: actual fields
    private Player you;
    private Player enemy;
    private int manaYou;
    private int manaEnemy;
    private int turnTime;
    private String activePlayer;
    private CardCollection yourSideOfPlayingField;
    private CardCollection enemySideOfPlayingField;
    /*private Hero you;
    private CardCollection deck;*/

    public void generateEnemy() {
        final List<String> HEROES = new ArrayList<>(Arrays.asList(this.getHeroNames()));
        int randomHeroIndex = (int) Math.round(Math.random()) * (HEROES.size() - 1);
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

    public Player getEnemy() {
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

    public void startAutoplayer() {
        enemy.getCardsInHand().addCard(enemy.getDeck().drawCard().getCardID());

        List<Card> cardsInHand = enemy.getCardsInHand().getCards();
        cardsInHand.sort(new manaCardCollectionComparator());


        double enemyBrain = (Math.random());
        ;
        if (enemyBrain <= 0.25) {

            enemy.getHero().executeHeroPower(enemy.getHero().getHeroPowerID(), getRandomTarget());
        }

        for (Card card : cardsInHand) {                                 //speel duurste kaarten eerst zolang je mana hebt
            if (card.getManaCost() <= manaEnemy) {
                enemy.playCard(card);
            }
        }

        for (Card card : enemy.getCardsOnPlayingField().getCards()) {
            //TODO check if card can attack


            if (you.getCardsOnPlayingField().getCards().isEmpty()) {
                card.attackHero(getYourHero());
            } else {
                card.attack(enemy.getRandomTargetMinion());
            }
        }
        //TODO zijn er end turn battlecries?
        setActivePlayer("you");
    }

    public Player activePlayerToPlayer() {
        if (activePlayer.equals("you")) {
            return you;
        } else {
            return enemy;
        }
    }

    public Object getRandomTarget() {
        int i;
        if (activePlayer.equals("enemy")) {
            i = yourSideOfPlayingField.getCards().size();
            int randomIndex = (int) (Math.round(Math.random()) * (i));
            if (randomIndex == i) {
                return you.getHero();
            }else {
                return yourSideOfPlayingField.getCards().get(i);
            }
        } else {
            i = enemySideOfPlayingField.getCards().size();
            int randomIndex = (int) (Math.round(Math.random()) * (i));
            if (randomIndex == i) {
                return enemy.getHero();
            }else {
                return enemySideOfPlayingField.getCards().get(i);
            }
        }
    }

    public void createPlayingField() {
        this.yourSideOfPlayingField = new CardCollection();
        this.enemySideOfPlayingField = new CardCollection();
    }

    public void turnMachine() {
        //TODO fix this
        /*Timer timer = new Timer();
        TimerTask set;
        timer.schedule(set, 50);*/
    }


}