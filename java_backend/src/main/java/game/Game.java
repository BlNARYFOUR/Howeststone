package game;


import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import hero.Hero;
import java.sql.*;
import java.util.*;

public class Game {
    private static final String YOU_STR = "you";
    private static final String ENEMY_STR = "enemy";
    private CardCollection beginCards;
    private CardCollection deckInDeckBuilder;
    private CardCollection filterCollection;

    private SqlDatabase db;

    private CardCollection allCards = new CardCollection("cards");
    private List<String> heroNames;
    private Map<String, List<CardCollection>> deckNames;

    // TODO: actual fields
    private Player you;
    private Player enemy;
    private int manaYou;
    private int manaEnemy;
    private TurnTimer turnTimer = new TurnTimer();
    private String activePlayer;
    private CardCollection yourSideOfPlayingField;
    private CardCollection enemySideOfPlayingField;

    /*private Hero you;
    private CardCollection deck;*/

    public CardCollection getDeck(String deckName) {
        final List<CardCollection> decks = deckNames.get(you.getHero().getHeroName());
        for (CardCollection deck: decks) {
            if (deck.getName().equals(deckName)) {
                return deck;
            }
        }
        return null;
    }


    public void generateEnemy() {
        final int randomHeroIndex = (int) Math.round(Math.random()) * (heroNames.size() - 1);
        final Player enemy = new Player();
        enemy.setHero(heroNames.get(randomHeroIndex));
        enemy.setDeck(deckNames.get(enemy.getHero().getHeroName()).get(0));
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

    public Boolean setPlayerCardsInHand(List<Integer> cardIDsInHand, List<Integer> cardIDsToReplace) {
        boolean isValidData = true;
        final CardCollection cardsInHand = new CardCollection();

        for (int gottencardID : cardIDsInHand) {
            if (!beginCards.hasCard(gottencardID)) {
                isValidData = false;
            }
        }

        if (isValidData) {
            for (int cardID : cardIDsInHand) {
                cardsInHand.addCard(beginCards.getCard(cardID));
            }
            for (int cardIDToReplace : cardIDsToReplace) {
                final Card card = beginCards.getCard(cardIDToReplace);
                cardsInHand.addCard(this.getYou().getDeck().drawCard());
                this.getYou().getDeck().addCard(card);
            }
            System.out.println("Cards in hand:\n" + cardsInHand);
            this.getYou().setCardsInHand(cardsInHand);
        }
        return isValidData;
    }

    public CardCollection getBeginCards() {
        return beginCards;
    }

    public CardCollection getDeckInDeckBuilder() {
        return deckInDeckBuilder;
    }

    public CardCollection getFilterCollection() {
        return filterCollection;
    }

    public CardCollection getAllCards() {
        return allCards;
    }

    public CardCollection getYourSideOfPlayingField() {
        return yourSideOfPlayingField;
    }

    public void setHeroNames(List<String> heroNames) {
        this.heroNames = heroNames;
    }

    public CardCollection getEnemySideOfPlayingField() {
        return enemySideOfPlayingField;
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

    public void setDeckNames(Map<String, List<CardCollection>> deckNames) {
        this.deckNames = deckNames;
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

    public int getTurnTimeLeft() {
        return turnTimer.getSecondsLeftThisTurn();
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }*/

    public void setDeckInDeckBuilder(String body) {
        deckInDeckBuilder = new CardCollection(body);
    }

    public void resetTurnTimer() {
        turnTimer.stop();
    }

    public void setFilterCollection(CardCollection cardCollection) {
        filterCollection = cardCollection;
    }

    public void setAllCards(CardCollection allCards) {
        this.allCards = allCards;
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
        you.beginTurn();
        //turnTimer.startTurnTimer();

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

    public void turnMachine() {
        //TODO fix this
        /*Timer timer = new Timer();
        TimerTask set;
        timer.schedule(set, 50);*/
    }

    public List<CardCollection> getDecks() {
        return deckNames.get(you.getHero().getHeroName());
    }

    public List<String> getDeckNames() {
        final List<String> deckNamesForChosenHero = new ArrayList<>();
        final List<CardCollection> decksForChosenHero = deckNames.get(you.getHero().getHeroName());
        for (CardCollection deck : decksForChosenHero) {
            deckNamesForChosenHero.add(deck.getName());
        }
        return deckNamesForChosenHero;
    }

    public void attackMinion(Minion attacker, Minion target) {
        final int attackValueAttacker = attacker.getAttack();
        final int healthValueAttacker = attacker.getHealth();
        final int attackValueTarget = target.getAttack();
        final int healthValueTarget = target.getAttack();

        target.setHealth(healthValueTarget - attackValueAttacker);
        attacker.setHealth(healthValueAttacker - attackValueTarget);
    }

    public CardCollection filterCards(List<String> filterArray) {
        final List<Integer> cardIDs = new ArrayList<>();

        final String everything = "%";
        final String notInArray = "-1";
        int filterManaCostMin = 0;
        int filterManaCostMax = 10;
        final String filterHeroName;
        final String filterType;
        final String filterRarity;

        if (!filterArray.get(0).equals("All")) {
            filterHeroName = filterArray.get(0) + everything;
        } else {
            filterHeroName = everything;
        }
        if (!filterArray.get(1).equals(notInArray)) {
            filterType = filterArray.get(1) + everything;
        } else {
            filterType = everything;
        }

        if (!filterArray.get(2).equals(notInArray)) {
            filterManaCostMin = Integer.parseInt(filterArray.get(2).split("_")[0]);
            if (filterManaCostMin == 7) {
                filterManaCostMax = 10;
            } else {
                filterManaCostMax = filterManaCostMin;
            }
        }
        if (!filterArray.get(3).equals(notInArray)) {
            filterRarity = filterArray.get(3) + everything;
        } else {
            filterRarity = everything;
        }

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.FILTER_CARDS)
        ) {
            stmt.setString(1, filterHeroName);
            stmt.setString(2, filterType);
            stmt.setInt(3, filterManaCostMin);
            stmt.setInt(4, filterManaCostMax);
            stmt.setString(5, filterRarity);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cardIDs.add(rs.getInt("cardID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCards.getSubCollection(cardIDs);
    }

    public void setDataBase(SqlDatabase db) {
        this.db = db;
    }

    public boolean checkIfDeckNotExist(String body) {
        final List<CardCollection> decksForChosenHero = deckNames.get(you.getHero().getHeroName());
        for (int i = 0; i < decksForChosenHero.size(); i++) {
            if (decksForChosenHero.get(i)
                    .getName().equals(body)) {
                return false;
            }
        }
        return true;
    }

    public String checkIfCardCanBeAddedToDeck(String body) {
        final int cardID = Integer.parseInt(body);
        for (int i = 0; i < this.allCards.getCards().size(); i++) {
            if (this.allCards.getCards().get(i).getCardID() == cardID) {
                final int heroId = this.allCards.getCards().get(i).getHeroId();
                final int yourHeroId = this.getYou().getHero().getHeroId();
                if (heroId != yourHeroId && heroId != 2) {
                    return "not the right hero";
                }
            }
        }
        return this.deckInDeckBuilder.checkIfCardCanBeAddedToDeck(body);
    }

    public boolean createDeck(CardCollection deck) {
        if (deck.getCards().size() != 30 || !checkIfNewDeckIsCorrect(deck)) {
            return false;
        }
        setDeckInDatabase(deck);
        return true;

    }

    private boolean checkIfNewDeckIsCorrect(CardCollection newDeck) {
        int check = 0;
        newDeck.getCards().sort(new CardCollectionAlphabeticalComparator());
        final List<Card> newDeckCards = newDeck.getCards();
        for (int i = 0; i < newDeckCards.size(); i++) {
            // TODO check on uncollectable
            if (i + 1 < newDeckCards.size()) {
                if (newDeckCards.get(i).getCardID() == newDeckCards.get(i + 1).getCardID()
                        && newDeckCards.get(i).getRarity().equals("Legendary")) {
                    check++;
                }
            }

            if (i + 2 < newDeckCards.size()) {
                if (newDeckCards.get(i).getCardID() == newDeckCards.get(i + 2).getCardID()) {
                    check++;
                }
            }
            if (newDeckCards.get(i).getHeroId() != you.getHero().getHeroId()
                    && newDeckCards.get(i).getHeroId() != 2) {
                check++;
            }
        }
        return check == 0;
    }

    private void setDeckInDatabase(CardCollection newDeck) {
        final String yourHeroName = this.getYou().getHero().getHeroName();
        final List<CardCollection> decks = deckNames.get(yourHeroName);
        decks.add(newDeck);
        deckNames.put(yourHeroName, decks);
        final int deckId = insertDeck(newDeck.getName(), this.getYou().getHero().getHeroId());
        setCardsInDatabase(newDeck, deckId);
    }

    private void setCardsInDatabase(CardCollection newDeck, int deckId) {
        int amountcheck = 0;
        for (int i = 0; i < newDeck.getCards().size(); i++) {
            if (i + 1 < newDeck.getCards().size()) {
                if (newDeck.getCards().get(i).getCardID() == newDeck.getCards().get(i + 1).getCardID()) {
                    insertCardToDeck(deckId, newDeck.getCards().get(i).getCardID(), 2);
                    amountcheck = 1;
                } else {
                    if (amountcheck == 0) {
                        insertCardToDeck(deckId, newDeck.getCards().get(i).getCardID(), 1);
                    }
                    amountcheck = 0;
                }
            } else {
                insertCardToDeck(deckId, newDeck.getCards().get(i).getCardID(), 1);
            }
        }
    }

    private void insertCardToDeck(int deckId, int cardID, int amount) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD_TO_DECK,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, deckId);
            stmt.setInt(2, cardID);
            stmt.setInt(3, amount);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No card added to deck: no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertDeck(String name, int heroId) {
        long deckId = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_DECK)
        ) {
            stmt.setString(1, name);
            stmt.setInt(2, heroId);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No deck created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    deckId = rs.getLong(1);
                } else {
                    throw new SQLException("No deck created: no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) deckId;
    }

    /*public void startYourTurn() {
        //TODO voer abilities uit aan begin turn
        you.beginTurn();

        while (turnTimer.getSecondsLeftThisTurn() <= 50) {
            // playCard(card)
            // attackMinion(..., ...);
            // useHeroPower()
            final boolean fixError = true;
        }
        setActivePlayer(ENEMY_STR);
        //startAutoplayer();
    }*/
}
