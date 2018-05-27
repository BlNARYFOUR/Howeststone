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
    private static final SqlDatabase DB = new SqlDatabase("jdbc:mysql://localhost:3306/howeststone", "root", "");
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private final Game howeststone = new Game();
    private final int port;

    Server(final int port) {
        this.port = port;
    }

    void start() {
        final Javalin server = Javalin.create()
                .port(port)
                .enableStaticFiles("web")
                .start();
        new Routes(server, howeststone);
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
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                howeststone.addHeroName(rs.getString("heroName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // getting all decks from db
        for (int i = 0; i < howeststone.getHeroNames().size(); i++) {
            try (
                    Connection conn = DB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_DECKS)
            ) {
                stmt.setString(1, howeststone.getHeroNames().get(i));
                final ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    howeststone.addDeckNames(howeststone.getHeroNames().get(i),
                            nameToCardCollection(rs.getString("deckName")));
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
        final CardCollection cards = new CardCollection(deckName);

        try (
                Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_CARD_IN_DECK)
        ) {
            stmt.setString(1, deckName);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                final int amount = rs.getInt("amount");
                final Card card;
                final String[] strArgs = new String[5];
                final int[] intArgs = new int[5];
                final int cardId = rs.getInt("cardId");
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
                        card = new Weapon(cardId, strArgs, intArgs);
                        break;
                    case "Spell":
                        card = new Spell(cardId, strArgs, intArgs);
                        break;
                    case "Minion":
                        card = new Minion(cardId, strArgs, intArgs);
                        break;
                    default:
                        throw new IllegalArgumentException("database not setup correctly");
                }
                for (int i = 0; i < amount; i++) {
                    System.out.println("NYI" + card);
                    cards.addCard(card);
                }
            }
            rs.close();
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
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                final int abilityId = rs.getInt("abilityId");
                final String abilityName = rs.getString("abilityName");
                // new ability
                // check if ability already exist (card)
                // add mechanics
                final int cardMechId = rs.getInt("cardMechId");
                final String target = rs.getString("target");
                final String mechValue = rs.getString("mechValue");
                final String mechanicType = rs.getString("mechanicType");

            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
