package api;

import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import game.Game;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    private static final SqlDatabase DB = new SqlDatabase("jdbc:mysql://localhost:3306/HOWESTSTONE", "root", "");
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
        // getting all heroes from db
        try (
                Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_HEROES)
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HOWESTSTONE.addHeroName(rs.getString("heroName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // getting all decks from db
        for (int i = 0; i < HOWESTSTONE.getHeroNames().size(); i++) {
            try (
                    Connection conn = DB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_DECKS)
            ) {
                stmt.setString(1, HOWESTSTONE.getHeroNames().get(i));
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    HOWESTSTONE.addDeckNames(HOWESTSTONE.getHeroNames().get(i), nameToCardCollection(rs.getString("deckName")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                String cardType = rs.getString("cardType");
                String cardName = rs.getString("cardName");
                String race = rs.getString("race");
                String urlOfImg = rs.getString("img");
                String rarity = rs.getString("rarity");
                int health = rs.getInt("health");
                int attack = rs.getInt("attack");
                int manaCost = rs.getInt("manaCost");
                int durability = rs.getInt("durability");
                int heroId = rs.getInt("heroId");
                switch (cardType) {
                    case "Weapon":
                        card = new Weapon(cardId, cardType, cardName, race,
                                urlOfImg, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "Spell":
                        card = new Spell(cardId, cardType, cardName, race,
                                urlOfImg, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "Minion":
                        card = new Minion(cardId, cardType, cardName, race,
                                urlOfImg, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    default:
                        throw new IllegalArgumentException("database not setup correctly");
                }
                for (int i = 0; i < amount ; i++) {
                    System.out.println("NYI" + card);
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
            stmt.setInt(1, card.getCardID());
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
