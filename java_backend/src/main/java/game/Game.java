package game;

import cards.Card;
import cards.CardCollection;
import cards.CardCollectionManaComparator;
import cards.Minion;
import hero.Hero;

import java.util.*;
public class Game {
    private static final String YOU_STR = "you";
    private static final String ENEMY_STR = "enemy";

    private CardCollection beginCards = new CardCollection();

    private List<String> heroNames = new ArrayList<>();
    private Map<String, CardCollection> deckNames = new HashMap<>();
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
        final int randomHeroIndex = (int) Math.round(Math.random()) * (heroNames.size() - 1);
        final Player enemy = new Player();
        enemy.setHero(heroNames.get(randomHeroIndex));
        enemy.setDeck(deckNames.get(enemy.getHero().getHeroName()));
        // TODO check if this works above
        this.addEnemy(enemy);
    }

    public CardCollection getPlayerBeginCards() {
        beginCards = new CardCollection();

        if (this.getActivePlayer().equals(ENEMY_STR)) {
            beginCards.addCard(this.getYou().getDeck().drawCard());
        }
        beginCards.addCard(this.getYou().getDeck().drawCard());
        beginCards.addCard(this.getYou().getDeck().drawCard());
        beginCards.addCard(this.getYou().getDeck().drawCard());

        return beginCards;
    }

    public Boolean setPlayerCardsInHand(List<Integer> cardIdsInHand, List<Integer> cardIdsToReplace) {
        boolean isValidData = true;
        final CardCollection cardsInHand = new CardCollection();

        for (int gottenCardID : cardIdsInHand) {
            if (!beginCards.hasCard(gottenCardID)) {
                isValidData = false;
            }
        }

        if (isValidData) {
            for (int cardId : cardIdsInHand) {
                cardsInHand.addCard(beginCards.getCard(cardId));
            }

            for (int cardIdToReplace : cardIdsToReplace) {
                final Card card = beginCards.getCard(cardIdToReplace);
                cardsInHand.addCard(this.getYou().getDeck().drawCard());
                this.getYou().getDeck().addCard(card);
            }

            System.out.println("Cards in hand:\n" + cardsInHand);

            this.getYou().setCardsInHand(cardsInHand);
        }

        return isValidData;
    }

    public Hero getYourHero() {
        return you.getHero();
    }

    public Hero getEnemyHero() {
        return enemy.getHero();
    }

    public List<String> getHeroNames() {
        return heroNames;
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

    public void startTurnAutoplayer() {

        final List<Card> cardsInHand = enemy.getCardsInHand().getCards();
        cardsInHand.sort(new CardCollectionManaComparator());

        final double enemyBrain = Math.random();
        if (enemyBrain <= 0.25) {
            enemy.getHero().executeHeroPower(enemy.getHero().getHeroPowerID(), getRandomTarget());
        }
        //speel duurste kaarten eerst zolang je mana hebt
        for (Card card : cardsInHand) {
            if (card.getManaCost() <= manaEnemy) {
                enemy.playCard(card.getCardID());
            }
        }
        for (Card card : enemy.getCardsOnPlayingField().getCards()) {
            if (!card.isExhausted()) {
                if (you.getCardsOnPlayingField().getCards().isEmpty()) {
                    card.attackHero(getYourHero());
                } else {
                    card.attack(enemy.getRandomTargetMinion());
                }
            }
        }
        //TODO zijn er end turn battlecries?
        setActivePlayer(YOU_STR);
        //TurnTimer.startTurnTimer();

    }

    public Player activePlayerToPlayer() {
        if (YOU_STR.equals(activePlayer)) {
            return you;
        } else {
            return enemy;
        }
    }

    public Object getRandomTarget() {
        final Object target;
        final int i;
        if (ENEMY_STR.equals(activePlayer)) {
            i = yourSideOfPlayingField.getCards().size() - 1;
            final int randomIndex = (int) (Math.round(Math.random()) * i);
            if (randomIndex == i) {
                target = you.getHero();
            } else {
                target = yourSideOfPlayingField.getCards().get(i);
            }
        } else {
            i = enemySideOfPlayingField.getCards().size() - 1;
            final int randomIndex = (int) (Math.round(Math.random()) * i);
            if (randomIndex == i) {
                target = enemy.getHero();
            } else {
                target = enemySideOfPlayingField.getCards().get(i);
            }
        }

        return target;
    }

    public void createPlayingField() {
        this.yourSideOfPlayingField = new CardCollection();
        this.enemySideOfPlayingField = new CardCollection();
    }

    public List<String> getDeckNames() {
        final List<String> deckNamesForChosenHero = new ArrayList<>();
        deckNamesForChosenHero.add(deckNames.get(you.getHero().getHeroName()).getName());
        return deckNamesForChosenHero;
    }

    public CardCollection getDeckByHeroName(String heroName) {
        return deckNames.get(heroName);
    }

    public void addDeckNames(String heroName, CardCollection cardCollection) {
        deckNames.put(heroName, cardCollection);
    }
    
    public void attackMinion(Minion attacker, Minion target) {
        final int attackValueAttacker = attacker.getAttack();
        final int healthValueAttacker = attacker.getHealth();
        final int attackValueTarget = target.getAttack();
        final int healthValueTarget = target.getAttack();

        target.setHealth(healthValueTarget - attackValueAttacker);
        attacker.setHealth(healthValueAttacker - attackValueTarget);
    }

    public void startYourTurn() {
        //TODO voer abilities uit aan begin turn
        you.beginTurn();

        while (turnTime <= 50) {
            // playCard(card)
            // attackMinion(..., ...);
            // useHeroPower()
            final boolean fixError = true;
        }
        setActivePlayer(ENEMY_STR);
        //startAutoplayer();
    }

    public void addHeroName(String heroName) {
        heroNames.add(heroName);
    }
}
