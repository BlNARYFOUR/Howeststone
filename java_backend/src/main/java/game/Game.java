package game;

import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import hero.Hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Game {

    private static SqlDatabase db = null;
    private CardCollection beginCards = new CardCollection();

    // TODO change

    public CardCollection allCards = new CardCollection("cards");
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
        deckNamesForChosenHero.add(deckNames.get(you.getHero().getHeroName()).getNameOfCardCollection());
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
        CardCollection cards = new CardCollection("filterCards");

        String filterHeroName = "";
        String filterType = "";
        int filterManaCost = -1;
        String filterRarity = "";
        if (!filterArray.get(0).equals("All")){
            filterHeroName = filterArray.get(0);
        }
        if (!filterArray.get(1).equals("-1")){
            filterType = filterArray.get(1);
        }
        if (!filterArray.get(2).equals("-1")){
            filterManaCost = Integer.parseInt(filterArray.get(2).split("_")[0]);
        }
        if (!filterArray.get(3).equals("-1")){
            filterRarity = filterArray.get(3);
        }
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.FILTER_CARDS)
        ) {
            stmt.setString(1, filterHeroName);
            stmt.setString(2, filterType);
            stmt.setInt(3, filterManaCost);
            stmt.setString(4, filterRarity);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Card card;
                int cardId = rs.getInt("cardId");
                String cardType = rs.getString("cardType");
                String cardName = rs.getString("cardName");
                String race = rs.getString("race");
                String img = rs.getString("img");
                String rarity = rs.getString("rarity");
                int health = rs.getInt("health");
                int attack = rs.getInt("attack");
                int manaCost = rs.getInt("manaCost");
                int durability = rs.getInt("durability");
                int heroId = rs.getInt("heroId");
                switch (cardType) {
                    case "Weapon":
                        card = new Weapon(cardId, cardName, race,
                                img, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "Spell":
                        card = new Spell(cardId, cardName, race,
                                img, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "Minion":
                        card = new Minion(cardId, cardName, race,
                                img, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    default:
                        throw new IllegalArgumentException("database not setup correctly");
                }
                cards.addCard(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(filterHeroName + filterType + filterManaCost + filterRarity);
        return new CardCollection();
    }

    public void setDataBase(SqlDatabase db) {
        this.db = db;
    }
}