package game;

import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import hero.Hero;

import java.sql.*;
import java.util.*;

public class Game {

    private static SqlDatabase db = null;
    private CardCollection beginCards = new CardCollection();
    public CardCollection deckInDeckBuilder = new CardCollection();
    public CardCollection filterCollection = new CardCollection();
    // TODO change

    public CardCollection allCards = new CardCollection("cards");
    public List<String> heroNames = new ArrayList<>();
    public Map<String, List<CardCollection>> deckNames = new HashMap<>();
    // TODO: actual fields
    private Player you;
    private Player enemy;
    private int manaYou;
    private int manaEnemy;
    private int turnTime;
    private String activePlayer;
    /*private Hero you;
    private CardCollection deck;*/

    public void generateEnemy() {
        int randomHeroIndex = (int) Math.round(Math.random()) * (heroNames.size() - 1);
        Player enemy = new Player();
        enemy.setHero(heroNames.get(randomHeroIndex));
        enemy.setDeck(deckNames.get(enemy.getHero().getHeroName()).get(0));
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

    public List<String> getDeckNames() {
        List<String> deckNamesForChosenHero = new ArrayList<>();
        List<CardCollection> decksForChosenHero = deckNames.get(you.getHero().getHeroName());
        for (int i = 0; i < decksForChosenHero.size(); i++) {
            deckNamesForChosenHero.add(decksForChosenHero.get(i).getNameOfCardCollection());
        }
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

    public CardCollection filterCards(List<String> filterArray) {
        List<Integer> cardIds = new ArrayList<>();

        String filterHeroName = "%";
        String filterType = "%";
        int filterManaCostMin = 0;
        int filterManaCostMax = 10;
        String filterRarity = "%";

        if (!filterArray.get(0).equals("All")) {
            filterHeroName = filterArray.get(0) + filterHeroName;
        }
        if (!filterArray.get(1).equals("-1")) {
            filterType = filterArray.get(1) + filterType;
        }
        if (!filterArray.get(2).equals("-1")) {
            filterManaCostMin = Integer.parseInt(filterArray.get(2).split("_")[0]);
            if (filterManaCostMin== 7){
                filterManaCostMax = 10;
            } else {
                filterManaCostMax = filterManaCostMin;
            }
        }
        if (!filterArray.get(3).equals("-1")) {
            filterRarity = filterArray.get(3) + filterRarity;
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
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cardIds.add(rs.getInt("cardId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCards.getSubCollection(cardIds);
    }

    public void setDataBase(SqlDatabase db) {
        this.db = db;
    }

    public boolean checkIfDeckNotExist(String body) {
        List<CardCollection> decksForChosenHero = deckNames.get(you.getHero().getHeroName());
        for (int i = 0; i < decksForChosenHero.size(); i++) {
            if (decksForChosenHero.get(i)
                    .getNameOfCardCollection().equals(body)) {
                return false;
            }
        }
        return true;
    }

    public String checkIfCardCanBeAddedToDeck(String body) {
        int cardId = Integer.parseInt(body);
        for (int i = 0; i < this.allCards.getCards().size(); i++) {
            if (this.allCards.getCards().get(i).getcardId() == cardId) {
                int heroId = this.allCards.getCards().get(i).getHeroId();
                int yourHeroId = this.getYou().getHero().getHeroId();
                if (heroId != yourHeroId && heroId != 2) {
                    return "not the right hero";
                }
            }
        }
        return this.deckInDeckBuilder.checkIfCardCanBeAddedToDeck(body);
    }

    public boolean createDeck(CardCollection deck) {
        if (deck.getCards().size() != 30) {
            return false;
        }
        if (!checkIfNewDeckIsCorrect(deck)) {
            return false;
        }
        setDeckInDatabase(deck);
        return true;

    }

    private boolean checkIfNewDeckIsCorrect(CardCollection newDeck) {
        newDeck.getCards().sort(new alfazCardCollectionComparator());
        List<Card> newDeckCards = newDeck.getCards();
        for (int i = 0; i < newDeckCards.size(); i++) {
            // TODO check on uncollectable
            if (i + 1 < newDeckCards.size()) {
                if (newDeckCards.get(i).getcardId() == newDeckCards.get(i + 1).getcardId()
                        && newDeckCards.get(i).getRarity().equals("Legendary")) {
                    return false;
                }
            }

            if (i + 2 < newDeckCards.size()) {
                if (newDeckCards.get(i).getcardId() == newDeckCards.get(i + 2).getcardId()) {
                    return false;
                }
            }
            if (newDeckCards.get(i).getHeroId() != you.getHero().getHeroId()
                    && newDeckCards.get(i).getHeroId() != 2) {
                return false;
            }
        }
        return true;
    }

    private void setDeckInDatabase(CardCollection newDeck) {
        String yourHeroName = this.getYou().getHero().getHeroName();
        List<CardCollection> decks = deckNames.get(yourHeroName);
        decks.add(newDeck);
        deckNames.put(yourHeroName, decks);
        int deckId = insertDeck(newDeck.getNameOfCardCollection(), this.getYou().getHero().getHeroId());
        setCardsInDatabase(newDeck, deckId);
    }

    private void setCardsInDatabase(CardCollection newDeck, int deckId) {
        for (int i = 0; i < newDeck.getCards().size(); i++) {
            if (i + 1 < newDeck.getCards().size()) {
                if (newDeck.getCards().get(i).getcardId() == newDeck.getCards().get(i + 1).getcardId()) {
                    insertCardToDeck(deckId, newDeck.getCards().get(i).getcardId(), 2);
                    i++;
                } else {
                    insertCardToDeck(deckId, newDeck.getCards().get(i).getcardId(), 1);
                }
            } else {
                insertCardToDeck(deckId, newDeck.getCards().get(i).getcardId(), 1);     }
        }
    }

    private void insertCardToDeck(int deckId, int cardId, int amount) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD_TO_DECK,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, deckId);
            stmt.setInt(2, cardId);
            stmt.setInt(3, amount);
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
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
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
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
}
