package api;

import abilities.Ability;
import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import game.Game;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setupDb.SetupDatabase;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Server {
    static final SqlDatabase DB = new SqlDatabase("jdbc:mysql://localhost:3306/HOWESTSTONE", "root", "");
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private final Game HOWESTSTONE = new Game();
    private final int port;

    Server(final int port) {
        this.port = port;
    }

    void start() {
        final Javalin server = Javalin.create()
                .port(port)
                .enableStaticFiles("web")
                .start();
        new Routes(server, HOWESTSTONE);
    }

    public static void main(final String[] args) {
        LOGGER.debug("starting server");
        new Server(4242).init().start();
        LOGGER.debug("server started");
    }

    private Server init() {
        HOWESTSTONE.setDataBase(DB);
        // getting all heroes from db
        try (
                Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_HEROES)
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HOWESTSTONE.heroNames.add(rs.getString("heroName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // getting all decks from db
        for (int i = 0; i < HOWESTSTONE.heroNames.size(); i++) {
            try (
                    Connection conn = DB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_DECKS)
            ) {
                stmt.setString(1, HOWESTSTONE.heroNames.get(i));
                ResultSet rs = stmt.executeQuery();
                List<CardCollection> decksForChosenHero = new ArrayList<>();
                while (rs.next()) {
                    decksForChosenHero.add(nameToCardCollection(rs.getString("deckName")));
                }
                HOWESTSTONE.deckNames.put(HOWESTSTONE.heroNames.get(i), decksForChosenHero);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try (
                Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_CARDS)
        ) {
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
                HOWESTSTONE.allCards.addCard(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // all cards
        // more?

        return this;
    }

    private CardCollection nameToCardCollection(String deckName) {
        // TODO return CardCollection
        CardCollection cards = new CardCollection(deckName);

        try (
                Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_CARD_IN_DECK)
        ) {
            stmt.setString(1, deckName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int amount = rs.getInt("amount");
                Card card;
                int cardId = rs.getInt("cardId");
                String cardType = rs.getString("cardType").toLowerCase();
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
                    case "weapon":
                        card = new Weapon(cardId, cardName, race,
                                img, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "spell":
                        card = new Spell(cardId, cardName, race,
                                img, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "minion":
                        card = new Minion(cardId, cardName, race,
                                img, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    default:
                        throw new IllegalArgumentException("database not setup correctly");
                }
                for (int i = 0; i < amount ; i++) {
                    cards.addCard(card);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private void addAbilitiesMechanicsToCard(Card card) {
        try (
                Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_ABILITIES_OF_CARD)
        ) {
            stmt.setInt(1, card.getcardId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int abilityId = rs.getInt("abilityId");
                String abilityName = rs.getString("abilityName");
                // new ability
                // check if ability already exist (card)
                // add mechanics
                int cardMechId = rs.getInt("cardMechId");
                String target= rs.getString("target");
                String mechValue = rs.getString("mechValue");
                String mechanicType = rs.getString("mechanicType");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
