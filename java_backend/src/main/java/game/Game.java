package game;

import cards.Card;
import cards.CardCollection;
import cards.manaCardCollectionComparator;
import cards.Minion;
import hero.Hero;

import java.util.*;
public class Game {
    private CardCollection beginCards = new CardCollection();

    public List<String> heroNames = new ArrayList<>();
    public Map<String , CardCollection> deckNames = new HashMap<>();
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

    public void generateEnemy(){
        int randomHeroIndex = (int)Math.round(Math.random())*(heroNames.size()-1);
        Player enemy = new Player();
        enemy.setHero(heroNames.get(randomHeroIndex));
        enemy.setDeck(deckNames.get(enemy.getHero().getHeroName()));
        // TODO check if this works above
        this.addEnemy(enemy);
    }

    public CardCollection getPlayerBeginCards() {
        beginCards = new CardCollection();

        if (this.getActivePlayer().equals("enemy")) {
            beginCards.addCard(this.getYou().getDeck().drawCard());
        }
        beginCards.addCard(this.getYou().getDeck().drawCard());
        beginCards.addCard(this.getYou().getDeck().drawCard());
        beginCards.addCard(this.getYou().getDeck().drawCard());

        return beginCards;
    }

    public Boolean setPlayerCardsInHand(List<Integer> cardIdsInHand, List<Integer> cardIdsToReplace) {
        boolean isValidData = true;
        CardCollection cardsInHand = new CardCollection();
        CardCollection cardsToReplace = new CardCollection();

        for(int gottenCardID : cardIdsInHand) {
            if(!beginCards.hasCard(gottenCardID)) {
                isValidData = false;
            }
        }

        if(isValidData) {
            for(int cardId : cardIdsInHand) {
                cardsInHand.addCard(beginCards.getCard(cardId));
            }

            for(int cardIdToReplace : cardIdsToReplace) {
                Card card = beginCards.getCard(cardIdToReplace);
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
        enemy.getCardsInHand().addCard(enemy.getDeck().drawCard());

        List<Card> cardsInHand = enemy.getCardsInHand().getCards();
        cardsInHand.sort(new manaCardCollectionComparator());


        double enemyBrain = (Math.random());
        ;
        if (enemyBrain <= 0.25) {

            enemy.getHero().executeHeroPower(enemy.getHero().getHeroPowerID(), getRandomTarget());
        }

        for (Card card : cardsInHand) {                                 //speel duurste kaarten eerst zolang je mana hebt
            if (card.getManaCost() <= manaEnemy) {
                enemy.playCard(card.getCardID());
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
        startTurnYou();

    }

    private void startTurnYou() {
        //TODO zorg dat kaarten kan gebruiken, targetten,  aanvallen, heropower gebruiken, ...
        //TODO zorg dat startTurnAutoPlayer() runt wanneer de 50 seconden om zijn
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

    public void startGame() {
        //TODO zorg dat bij replacecard de beginner de activeplayer wordt
        if (activePlayer.equals("enemy")) {
            startTurnAutoplayer();
        } else {
            startTurnYou();
        }
    }

    public List<String> getDeckNames() {
        List<String> deckNamesForChosenHero = new ArrayList<>();
        deckNamesForChosenHero.add(deckNames.get(you.getHero().getHeroName()).getName());
        return deckNamesForChosenHero;
    }
    
    public void attackMinion(Minion attacker, Minion target) {
        int attackValueAttacker = attacker.getAttack();
        int healthValueAttacker = attacker.getHealth();
        int attackValueTarget = target.getAttack();
        int healthValueTarget = target.getAttack();

        target.setHealth(healthValueTarget - attackValueAttacker);
        attacker.setHealth(healthValueAttacker - attackValueTarget);
    }
}