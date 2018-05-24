package api;

import cards.*;
import db.SqlDatabase;
import db.SqlStatements;
import game.Game;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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
                while (rs.next()) {
                    HOWESTSTONE.deckNames.put(HOWESTSTONE.heroNames.get(i), nameToCardCollection(rs.getString("deckName")));
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
                String cardType = rs.getString("cardType").toLowerCase();
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
                    case "weapon":
                        card = new Weapon(cardId, cardName, race,
                                urlOfImg, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "spell":
                        card = new Spell(cardId, cardName, race,
                                urlOfImg, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    case "minion":
                        card = new Minion(cardId, cardName, race,
                                urlOfImg, rarity, health, attack, manaCost,
                                durability, heroId);
                        break;
                    default:
                        throw new IllegalArgumentException("database not setup correctly");
                }
                for (int i = 0; i < amount ; i++) {
                    System.out.println("NYI" + card);
                    addAbilitiesMechanicsToCard(card);
                    // cards.addCard(card);
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
