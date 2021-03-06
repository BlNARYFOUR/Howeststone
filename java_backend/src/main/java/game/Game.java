package game;


import abilities.*;
import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import formatters.ColorFormats;
import hero.Hero;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private static final String YOU_STR = "you";
    private static final String ENEMY_STR = "enemy";
    private CardCollection beginCards;
    private CardCollection deckInDeckBuilder;
    private CardCollection filterCollection;
    private boolean isGameActive;

    private final SqlDatabase db;

    private CardCollection allCards;
    private List<String> heroNames;
    private Map<String, List<CardCollection>> deckNames;

    // TODO: actual fields
    private Player you;
    private Player enemy;
    private int manaYou;
    private int manaEnemy;
    private TurnTimer turnTimer;
    private String activePlayer;
    private CardCollection yourSideOfPlayingField;
    private CardCollection enemySideOfPlayingField;

    public Game(SqlDatabase db) {
        this.db = db;
        this.setHeroNames(this.getHeroNamesOutOfDb());
        this.setDeckNames(this.getDecksOutOfDb());
        this.setAllCards(this.getAllCardsOutOfDb());
        isGameActive = false;
        turnTimer = new TurnTimer();
    }

    private List<String> getHeroNamesOutOfDb() {
        // getting all heroes from db
        final List<String> heroNames = new ArrayList<>();
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_HEROES)
        ) {
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                heroNames.add(rs.getString("heroName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroNames;
    }

    private Map<String, List<CardCollection>> getDecksOutOfDb() {
        // getting all decks from db
        final Map<String, List<CardCollection>> deckNames = new HashMap<>();
        for (int i = 0; i < this.getHeroNames().size(); i++) {
            try (
                    Connection conn = db.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_DECKS)
            ) {
                stmt.setString(1, this.getHeroNames().get(i));
                final ResultSet rs = stmt.executeQuery();
                final List<CardCollection> decksForChosenHero = new ArrayList<>();
                while (rs.next()) {
                    decksForChosenHero.add(nameToCardCollection(rs.getString("deckName")));

                }
                deckNames.put(this.getHeroNames().get(i), decksForChosenHero);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deckNames;
    }

    private CardCollection nameToCardCollection(String deckName) {
        final CardCollection cards = new CardCollection(deckName);
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_CARD_IN_DECK)
        ) {
            stmt.setString(1, deckName);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                final int amount = rs.getInt("amount");
                for (int i = 0; i < amount; i++) {
                    cards.addCard(getCardOutOfRs(rs));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private Card getCardOutOfRs(ResultSet rs) throws SQLException {
        final Card card;
        final String[] strArgs = new String[5];
        final int[] intArgs = new int[5];
        final int cardID = rs.getInt("cardID");
        strArgs[0] = rs.getString("cardType");
        strArgs[1] = rs.getString("cardName");
        strArgs[2] = rs.getString("race");
        strArgs[3] = rs.getString("img");
        strArgs[4] = rs.getString("rarity");
        intArgs[0] = rs.getInt("health");
        intArgs[1] = rs.getInt("attack");
        intArgs[2] = rs.getInt("manaCost");
        intArgs[3] = rs.getInt("durability");
        intArgs[4] = rs.getInt("heroId");
        switch (strArgs[0]) {
            case "Weapon":
                card = new Weapon(cardID, strArgs, intArgs);
                break;
            case "Spell":
                card = new Spell(cardID, strArgs, intArgs);
                break;
            case "Minion":
                card = new Minion(cardID, strArgs, intArgs);
                break;
            default:
                throw new IllegalArgumentException("database not setup correctly");
        }
        addAbilitiesToCard(card);
        return card;
    }

    private CardCollection getAllCardsOutOfDb() {
        final CardCollection allCards = new CardCollection();
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.SELECT_CARDS)
        ) {
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allCards.addCard(getCardOutOfRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCards;
    }

    private void addAbilitiesToCard(Card card) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_ABILITIES_OF_CARD)
        ) {

            stmt.setInt(1, card.getCardID());
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                final int abilityId = rs.getInt("abilityId");
                final String abilityName = rs.getString("abilityName");
                final List<Ability> abilities = new ArrayList<>();
                Ability ability;
                switch (abilityName) {
                    case "Uncollectable":
                        ability = new Uncollectable(Abilities.UNCOLLECTABLE);
                        abilities.add(ability);
                        break;
                    case "Charge":
                        ability = new Charge(Abilities.CHARGE);
                        abilities.add(ability);
                        break;
                    case "Divine Shield":
                        ability = new DivineShield(Abilities.DIVINE_SHIELD);
                        abilities.add(ability);
                        break;
                    case "Windfury":
                        ability = new Windfury(Abilities.WINDFURY);
                        abilities.add(ability);
                        break;
                    default:
                        break;
                }
                card.setAbilities(abilities);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInactive() {
        isGameActive = false;
    }

    public void setActive() {
        isGameActive = true;
    }

    public String beginGame() {
        if (getYou().getHero() == null || getYou().getDeck() == null) {
            throw new NullPointerException();
        }
        setActive();
        System.out.println(ColorFormats.green("Game currently active"));

        generateEnemy();
        getYou().resetMana();
        setTurnTime(50);
        createPlayingField();

        final Random rand = new Random();
        final boolean doYouBegin = rand.nextBoolean();
        System.out.println("I begin: " + doYouBegin);
        if (doYouBegin) {
            setActivePlayer(YOU_STR);
            return YOU_STR;
        } else {
            setActivePlayer(ENEMY_STR);
            return ENEMY_STR;
        }
    }

    public CardCollection getDeck(String deckName) {
        final List<CardCollection> decks = deckNames.get(you.getHero().getHeroName());
        for (CardCollection deck : decks) {
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

    private void setHeroNames(List<String> heroNames) {
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

    private void setDeckNames(Map<String, List<CardCollection>> deckNames) {
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

    private void addEnemy(Player enemy) {
        this.enemy = enemy;
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

    public void setTurnTime(int seconds) {
        this.turnTimer.setCountDownTurnTimer(seconds);
    }

    public void setDeckInDeckBuilder(String body) {
        deckInDeckBuilder = new CardCollection(body);
    }

    public void resetTurnTimer() {
        turnTimer.stop();
    }

    public void setFilterCollection(CardCollection cardCollection) {
        filterCollection = cardCollection;
    }

    private void setAllCards(CardCollection allCards) {
        this.allCards = allCards;
    }

    public void startTurnAutoplayer() {
        setActivePlayer(ENEMY_STR);
        getEnemy().beginTurn();
        System.out.println("Begins Enemy turn :)");
        //turnTimer.startTurnTimer(this::onEnemyTurnEnd);

        // final List<Card> cardsInHand = enemy.getCardsInHand().getCards();
        // cardsInHand.sort(new CardCollectionManaComparator());

        final double enemyBrain = Math.random();
        if (enemyBrain <= 0.25) {
            enemy.getHero().executeHeroPower(enemy.getHero().getHeroPowerID(), getRandomTarget());
        }
        //speel duurste kaarten eerst zolang je mana hebt
        final CardCollection copy = new CardCollection(enemy.getCardsInHand());
        copy.getCards().sort(new CardCollectionManaComparator());
        for (Card card : copy.getCards()) {
            System.out.println(card);
            if (7 <= enemy.getCardsOnPlayingField().size()) {
                break;
            }

            if (card.getManaCost() <= enemy.getActiveMana()) {
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
        if (isGameActive) {
            startTurnYou();
        }
    }

    public void startTurnYou() {
        setActivePlayer(YOU_STR);
        you.beginTurn();
        System.out.println("Begins your turn :)");
        turnTimer.startTurnTimer(this::onYourTurnEnd);
    }

    private void onEnemyTurnEnd() {
        System.out.println(ColorFormats.green("Enemy time is up :)"));

        startTurnYou();
    }

    private void onYourTurnEnd() {
        System.out.println(ColorFormats.green("Your time is up :'("));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startTurnAutoplayer();
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
            final int randomIndex = (int) Math.round(Math.random() * i);
            if (randomIndex == i || i < 0) {
                target = you.getHero();
            } else {
                target = yourSideOfPlayingField.getCards().get(i);
            }
        } else {
            i = enemySideOfPlayingField.getCards().size() - 1;
            final int randomIndex = (int) Math.round(Math.random() * i);
            if (randomIndex == i || i < 0) {
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
        if (you.getHero() == null) {
            throw new NullPointerException();
        }
        return deckNames.get(you.getHero().getHeroName());
    }

    public List<String> getDeckNamesForChosenHero() {
        if (you.getHero() == null) {
            throw new NullPointerException();
        }
        final List<String> deckNamesForChosenHero = new ArrayList<>();
        final List<CardCollection> decksForChosenHero = deckNames.get(you.getHero().getHeroName());
        for (CardCollection deck : decksForChosenHero) {
            deckNamesForChosenHero.add(deck.getName());
        }
        return deckNamesForChosenHero;
    }
    public void attackMinion(String yourId, String targetId) {
        // TODO check if cards exist
        // TODO attack
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
            // Without:  bugError, with, error because to much lines...
            // rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCards.getSubCollection(cardIDs);
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
        String result = "";
        final int cardID = Integer.parseInt(body);
        for (int i = 0; i < this.allCards.getCards().size(); i++) {
            if (this.allCards.getCards().get(i).getCardID() == cardID) {
                final int heroId = this.allCards.getCards().get(i).getHeroId();
                final int yourHeroId = this.getYou().getHero().getHeroId();
                Ability uncollectable = new Uncollectable(Abilities.UNCOLLECTABLE);
                if (this.allCards.getCards().get(i).getCardAbilities().contains(uncollectable)) {
                    result = "card cannot be used in game";
                }
                if (heroId != yourHeroId && heroId != 2) {
                    result = "not the right hero";
                }
            }
        }
        if ("".equals(result)) {
            result = this.deckInDeckBuilder.checkIfCardCanBeAddedToDeck(body);
        }
        return result;
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
            final Ability uncollectable = new Uncollectable(Abilities.UNCOLLECTABLE);
            if (newDeckCards.get(i).getCardAbilities().contains(uncollectable)) {
                check++;
            }
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
        boolean previousCardCheck = false;
        for (int i = 0; i < newDeck.getCards().size(); i++) {
            if (i + 1 < newDeck.getCards().size()) {
                if (newDeck.getCards().get(i).getCardID() == newDeck.getCards().get(i + 1).getCardID()) {
                    insertCardToDeck(deckId, newDeck.getCards().get(i).getCardID(), 2);
                    previousCardCheck = true;
                } else {
                    if (!previousCardCheck) {
                        insertCardToDeck(deckId, newDeck.getCards().get(i).getCardID(), 1);
                    }
                    previousCardCheck = false;
                }
            } else {
                if (!previousCardCheck) {
                    insertCardToDeck(deckId, newDeck.getCards().get(i).getCardID(), 1);
                }
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

    public void attackEnemyHero(String body) {
        // TODO check if card can attack
        allCards.getCard(Integer.parseInt(body)).attackHero(enemy.getHero());
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
