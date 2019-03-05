package setupdatabase;

import db.SqlDatabase;
import db.SqlStatements;
import formatters.ColorFormats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public final class SetupDatabase {

    private static final int MAX_DECK_SIZE = 30;

    private static final String SPELLS_LOCATION = "json/spells.json";
    private static final String MINIONS_LOCATION = "json/minions.json";
    private static final String WEAPONS_LOCATION = "json/weapons.json";

    private static final String NEUTRAL = "Neutral";
    private static final String[] HEROES = {NEUTRAL, "Mage", "Paladin"};
    private static final String[] ABILITIES_WITH_MECHANICS = {"Aura", "Battlecry", "Deathrattle",
        "DestroyedDivineShield", "Enrage", "On Attack", "On Death", "On Heal", "On Hit",
        "TurnTimer Begin", "TurnTimer End", "Untargetable", "Update In Hand"};

    // STRING REPETITION SOLVERS
    private static final String EXCLAMATION_STR = "!";
    private static final String NO_STR = "No ";
    private static final String NOW_HAS_ID_STR = "\t* '%s' now has ID %d%n";
    private static final String VALUE_STR = "value";
    private static final String TARGET_STR = "target";
    private static final String MECHANICS_STR = "mechanics";
    private static final String ABILITIES_STR = "abilities";
    private static final String TYPE_STR = "type";
    private static final String NULL_STR = "NULL";
    private static final String NAME_STR = "name";
    private static final String LIST_ITEM_STR = "\t* ";
    private static final String DECK_CREATED_STR = "deck created: ";
    private static final String CARD_MECHANIC_CREATED_STR = "cardMechanic created: ";
    private static final String ID_OBTAINED_STR = "ID obtained.";
    private static final String ROWS_AFFECTED_STR = "rows affected.";
    private static final String CARD_CREATED_STR = "card created: ";

    private SqlDatabase db = new SqlDatabase("jdbc:mysql://localhost:3306", "root", "root");
    private JSONArray spellList;
    private JSONArray minionList;
    private JSONArray weaponList;

    private SetupDatabase() {
        try {
            spellList = getJsonList(SPELLS_LOCATION);
            minionList = getJsonList(MINIONS_LOCATION);
            weaponList = getJsonList(WEAPONS_LOCATION);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SetupDatabase().run();
    }

    private void run() {
        initDatabase();
        addAbilitiesToDb();
        addHeroesToDb();
        addMechanicsToDb();
        listAllMechanicTargets();
        listAllMechanicValues();
        addCardsToDb();
        createStandardDecks();
    }

    private void addAbilitiesToDb() {
        final Set<String> abilities = new HashSet<>();

        addAbilitiesToSet(spellList, abilities);
        addAbilitiesToSet(minionList, abilities);
        addAbilitiesToSet(weaponList, abilities);

        System.out.println("Abilities:");
        outputSet(abilities);

        System.out.println("\nAdding abilities to database...");
        final List<String> abilityList = new ArrayList<>(abilities);
        Collections.sort(abilityList);
        for (String ability : abilityList) {
            insertAbility(ability);
        }
        System.out.println(ColorFormats.blue("abilities have been added!"));
    }

    private void addHeroesToDb() {
        System.out.println("\nHeroes:");
        outputList(Arrays.asList(HEROES));

        System.out.println("\nAdding heroes to database...");
        for (String hero : HEROES) {
            insertHero(hero);
        }
        System.out.println(ColorFormats.blue("heroes have been added!"));
    }

    private void addMechanicsToDb() {
        final Set<String> mechanics = new HashSet<>();

        addMechanicTypeToSet(spellList, mechanics);
        addMechanicTypeToSet(weaponList, mechanics);
        addMechanicTypeToSet(minionList, mechanics);

        System.out.println("\nMechanics:");
        outputSet(mechanics);

        System.out.println("\nAdding mechanics to database...");
        final List<String> mechanicList = new ArrayList<>(mechanics);
        Collections.sort(mechanicList);
        for (String mechanic : mechanicList) {
            insertMechanic(mechanic);
        }
        System.out.println(ColorFormats.blue("mechanics have been added!"));
    }

    private void addCardsToDb() {
        System.out.println("\nAdding cards...");
        addCardFromJsonArray(spellList);
        addCardFromJsonArray(minionList);
        addCardFromJsonArray(weaponList);
        System.out.println(ColorFormats.blue("cards have been added!"));
    }

    private void addCardFromJsonArray(JSONArray cards) {
        for (Object card : cards) {
            final JSONObject jsonCard = (JSONObject) card;

            final String[] strArgs = new String[5];
            final int[] intArgs = new int[5];

            //noinspection unchecked
            strArgs[0] = jsonCard.getOrDefault(NAME_STR, NULL_STR).toString();
            //noinspection unchecked
            strArgs[1] = jsonCard.getOrDefault(TYPE_STR, NULL_STR).toString();
            //noinspection unchecked
            strArgs[2] = jsonCard.getOrDefault("race", NULL_STR).toString();
            //noinspection unchecked
            strArgs[3] = jsonCard.getOrDefault("img", NULL_STR).toString();
            //noinspection unchecked
            strArgs[4] = jsonCard.getOrDefault("rarity", NULL_STR).toString();
            //noinspection unchecked
            intArgs[0] = Integer.parseInt(jsonCard.getOrDefault("health", 0).toString());
            //noinspection unchecked
            intArgs[1] = Integer.parseInt(jsonCard.getOrDefault("attack", 0).toString());
            //noinspection unchecked
            intArgs[2] = Integer.parseInt(jsonCard.getOrDefault("cost", 0).toString());
            //noinspection unchecked
            intArgs[3] = Integer.parseInt(jsonCard.getOrDefault("durability", 0).toString());
            //noinspection unchecked
            intArgs[4] = getHeroId(jsonCard.getOrDefault("playerClass", NULL_STR).toString());

            if (0 <= intArgs[4]) {
                final int cardID = insertCard(strArgs, intArgs);
                addCardAbilityToDb(jsonCard, cardID);
            }
        }
    }

    private void addCardAbilityToDb(JSONObject card, int cardID) {
        //noinspection unchecked
        final JSONArray jsonAbilities = (JSONArray) card.getOrDefault(ABILITIES_STR, null);

        if (jsonAbilities != null) {
            for (Object subItemObj : jsonAbilities) {
                final JSONObject jsonAbility = (JSONObject) subItemObj;
                //noinspection unchecked
                final String abilityName = jsonAbility.get(NAME_STR).toString();

                final int abilityId = getAbilityId(abilityName);

                if (0 <= abilityId) {
                    int cardMechId = 1;

                    if (Arrays.asList(ABILITIES_WITH_MECHANICS).contains(abilityName)) {
                        //noinspection unchecked
                        final JSONArray jsonMechanics = (JSONArray) card.getOrDefault(MECHANICS_STR, null);
                        //noinspection unchecked
                        final int mechNeeded = Integer.parseInt(jsonAbility.getOrDefault(
                                "mechNeeded", jsonMechanics.size()).toString());

                        for (int i = 0; i < mechNeeded; i++) {
                            final Object obj = jsonMechanics.get(i);

                            final JSONObject jsonMechanic = (JSONObject) obj;
                            //noinspection unchecked
                            final String mechanicType = jsonMechanic.get(TYPE_STR).toString();
                            //noinspection unchecked
                            final String target = jsonMechanic.getOrDefault(TARGET_STR, NULL_STR).toString();
                            //noinspection unchecked
                            final String value = jsonMechanic.getOrDefault(VALUE_STR, NULL_STR).toString();

                            final int mechanicId = getMechanicId(mechanicType);

                            if (0 <= mechanicId) {
                                cardMechId = insertCardMechanic(mechanicId, target, value);
                                insertCardAbility(abilityId, cardID, cardMechId);
                            }
                        }
                    } else {
                        insertCardAbility(abilityId, cardID, cardMechId);
                    }
                }
            }
        }
    }

    private void createStandardDecks() {
        System.out.println("\nCreating standard decks...");
        for (String heroName : HEROES) {
            if (!heroName.equals(NEUTRAL)) {
                createRandomDeck("Standard " + heroName, getHeroId(heroName));
            }
        }
        System.out.println(ColorFormats.blue("standard decks have been created!"));
    }

    private void listAllMechanicTargets() {
        final Set<String> mechanics = new HashSet<>();

        addMechanicTargetToSet(spellList, mechanics);
        addMechanicTargetToSet(weaponList, mechanics);
        addMechanicTargetToSet(minionList, mechanics);

        System.out.println("\nTargets:");
        outputSet(mechanics);
    }

    private void listAllMechanicValues() {
        final Set<String> mechanics = new HashSet<>();

        addMechanicValueToSet(spellList, mechanics);
        addMechanicValueToSet(weaponList, mechanics);
        addMechanicValueToSet(minionList, mechanics);

        System.out.println("\nValues:");
        outputSet(mechanics);
    }

    public void initDatabase() {
        executeStatement(SqlStatements.DROP_DB, "Old DB drop");
        executeStatement(SqlStatements.CREATE_DB, "Creating new DB");
        executeStatement(SqlStatements.CREATE_MECHANICS, "Creating Mechanics");
        executeStatement(SqlStatements.CREATE_ABILITIES, "Creating Abilities");
        executeStatement(SqlStatements.CREATE_HEROES, "Creating Heroes");
        executeStatement(SqlStatements.CREATE_CARDS, "Creating Cards");
        executeStatement(SqlStatements.CREATE_CARD_MECHANICS, "Creating CardMechanics");
        executeStatement(SqlStatements.CREATE_CARD_ABILITIES, "Creating CardAbilities");
        executeStatement(SqlStatements.CREATE_DECKS, "Creating Decks");
        executeStatement(SqlStatements.CREATE_CARDS_IN_DECKS, "Creating CardsInDecks");
        executeStatement(SqlStatements.INSERT_NONE_MECHANIC, "Inserting none mechanic");
        executeStatement(SqlStatements.INSERT_NONE_CARD_MECHANIC, "Inserting none cardMechanic");
    }

    public void executeStatement(String statement, String name) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(statement,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows < 0) {
                throw new SQLException(name + " failed :'(");
            } else {
                System.out.println(name + " succeeded!");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void insertMechanic(String mechanic) {
        insertSingleValue(mechanic, SqlStatements.INSERT_MECHANIC, "mechanic");
    }

    private void insertAbility(String ability) {
        insertSingleValue(ability, SqlStatements.INSERT_ABILITY, "ability");
    }

    private void insertHero(String hero) {
        insertSingleValue(hero, SqlStatements.INSERT_HERO, "hero");
    }

    private int insertCard(String[] strArgs, int[] intArgs) {
        long id = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            if (strArgs.length == 5 && intArgs.length == 5) {
                for (int i = 0; i < strArgs.length; i++) {
                    stmt.setString(i + 1, strArgs[i]);
                }

                for (int i = 0; i < intArgs.length; i++) {
                    stmt.setInt(i + 6, intArgs[i]);
                }
            }

            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException(NO_STR + CARD_CREATED_STR + NO_STR + ROWS_AFFECTED_STR);
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                    System.out.printf(NOW_HAS_ID_STR, strArgs[0], id);
                } else {
                    throw new SQLException(NO_STR + CARD_CREATED_STR + NO_STR + ID_OBTAINED_STR);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) id;
    }

    private void insertSingleValue(String value, String statement, String name) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(statement,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, value);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException(NO_STR + name + " created: NO_STR rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final long id = rs.getLong(1);
                    System.out.printf(NOW_HAS_ID_STR, value, id);
                } else {
                    throw new SQLException(NO_STR + name + " created: NO_STR ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertCardMechanic(int mechanicId, String target, String value) {
        long cardMechId = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD_MECHANIC,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, mechanicId);
            stmt.setString(2, target);
            stmt.setString(3, value);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException(NO_STR + CARD_MECHANIC_CREATED_STR + NO_STR + ROWS_AFFECTED_STR);
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cardMechId = rs.getLong(1);
                    System.out.printf("\t* new cardMechanic now has ID %d%n", cardMechId);
                } else {
                    throw new SQLException(NO_STR + CARD_MECHANIC_CREATED_STR + NO_STR + ID_OBTAINED_STR);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) cardMechId;
    }

    private void insertCardAbility(int abilityId, int cardID, int cardMechId) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD_ABILITY)
        ) {
            stmt.setInt(1, abilityId);
            stmt.setInt(2, cardID);
            stmt.setInt(3, cardMechId);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException(NO_STR + "cardAbility created: " + NO_STR + ROWS_AFFECTED_STR);
            } else {
                System.out.println("\t* new cardAbility has been added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createRandomDeck(String name, int heroId) {
        final int deckId = insertDeck(name, heroId);
        final int amountOfCards = getTotalAmountOfCards();
        int cardID;

        for (int i = 0; i < MAX_DECK_SIZE; i++) {
            do {
                cardID = 1 + (int) Math.round(Math.random() * (amountOfCards - 1));
            } while (!addCardToDeck(deckId, cardID));
        }
    }

    private boolean addCardToDeck(int deckId, int cardID) {
        final int amount = getAmountOfCardInDeck(deckId, cardID);
        boolean succeeded = false;

        if (deckCanContainMoreThenOneOfCard(cardID, deckId, amount)) {
            updateAmountOfCardInDeck(deckId, cardID, amount + 1);
            succeeded = true;
        } else if (amount < 0 && !isUncollectable(cardID) && doCardAndDeckHeroMatch(cardID, deckId)) {
            insertCardToDeck(deckId, cardID, 1);
            succeeded = true;
        }

        return succeeded;
    }

    private boolean deckCanContainMoreThenOneOfCard(int cardId, int deckId, int amount) {
        boolean result = 0 <= amount && amount <= 1;
        result = result && !isLegendary(cardId) && !isUncollectable(cardId) && doCardAndDeckHeroMatch(cardId, deckId);

        return result;
    }

    private boolean doCardAndDeckHeroMatch(int cardID, int deckId) {
        boolean result = false;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.TEST_DECK_AND_CARD_MATCH);
        ) {
            stmt.setInt(1, cardID);
            stmt.setInt(2, deckId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBoolean("TRUE");
                } else {
                    System.out.println(ColorFormats.red("\t* Could not test heroIds for deck and card!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean isUncollectable(int cardID) {
        boolean result = false;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.IS_UNCOLLECTABLE);
        ) {
            stmt.setInt(1, cardID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBoolean("isUncollectable");
                } else {
                    System.out.println(ColorFormats.red("\t* Could not read the abilities of this card!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean isLegendary(int cardID) {
        boolean result = false;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.IS_LEGENDARY);
        ) {
            stmt.setInt(1, cardID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBoolean("isLegendary");
                } else {
                    System.out.println(ColorFormats.red("\t* Could not read the rarity of this card!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void updateAmountOfCardInDeck(int deckId, int cardID, int amount) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.UPDATE_AMOUNT_OF_CARD_IN_DECK,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, amount);
            stmt.setInt(2, deckId);
            stmt.setInt(3, cardID);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException(NO_STR + "amount of cards has been updated: " + NO_STR + ROWS_AFFECTED_STR);
            } else {
                System.out.println("Card amount has been updated in deck!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCardToDeck(int deckId, int cardID, int amount) {
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
                throw new SQLException(NO_STR + "card added to deck: " + NO_STR + ROWS_AFFECTED_STR);
            } else {
                System.out.println("Card has been added to deck!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertDeck(String name, int heroId) {
        long deckId = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_DECK)
        ) {
            stmt.setString(1, name);
            stmt.setInt(2, heroId);
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException(NO_STR + DECK_CREATED_STR + NO_STR + ROWS_AFFECTED_STR);
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    deckId = rs.getLong(1);
                    System.out.printf("\t* new deck now has ID %d%n", deckId);
                } else {
                    throw new SQLException(NO_STR + DECK_CREATED_STR + NO_STR + ID_OBTAINED_STR);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) deckId;
    }

    private int getTotalAmountOfCards() {
        int num = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.GET_TOTAL_AMOUNT_OF_CARDS);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    num = rs.getInt("amountOfCards");
                } else {
                    System.out.println(ColorFormats.red("\t* Could not read the amount of cards!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    private int getAmountOfCardInDeck(int deckId, int cardID) {
        int num = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.SELECT_AMOUNT_OF_CARDS_IN_DECK);
        ) {
            stmt.setInt(1, deckId);
            stmt.setInt(2, cardID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    num = rs.getInt("amount");
                } else {
                    System.out.println(ColorFormats.red(LIST_ITEM_STR + NO_STR + "amount found for cardID "
                            + cardID + " in deck " + deckId + EXCLAMATION_STR));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    private int getDeckId(String deckName) {
        return getSingleInt(deckName, SqlStatements.SELECT_DECK_ID, "deckId");
    }

    private int getHeroId(String heroName) {
        return getSingleInt(heroName, SqlStatements.SELECT_HERO_ID, "heroId");
    }

    private int getMechanicId(String mechanicType) {
        return getSingleInt(mechanicType, SqlStatements.SELECT_MECHANIC_ID, "mechanicId");
    }

    private int getAbilityId(String abilityName) {
        return getSingleInt(abilityName, SqlStatements.SELECT_ABILITY_ID, "abilityId");
    }

    private int getSingleInt(String value, String statement, String name) {
        int num = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(statement);
        ) {
            stmt.setString(1, value);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    num = rs.getInt(name);
                } else {
                    System.out.println(ColorFormats.red(LIST_ITEM_STR + NO_STR + name
                            + " found for " + value + EXCLAMATION_STR));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    private Set<String> addAbilitiesToSet(JSONArray jsonArray, Set<String> prevFoundAbilities) {
        return addSubItemsToSet(jsonArray, prevFoundAbilities, ABILITIES_STR, NAME_STR);
    }

    private Set<String> addMechanicTypeToSet(JSONArray jsonArray, Set<String> prevFoundMechanicTypes) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicTypes, MECHANICS_STR, TYPE_STR);
    }

    private Set<String> addMechanicTargetToSet(JSONArray jsonArray, Set<String> prevFoundMechanicTargets) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicTargets, MECHANICS_STR, TARGET_STR);
    }

    private Set<String> addMechanicValueToSet(JSONArray jsonArray, Set<String> prevFoundMechanicValues) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicValues, MECHANICS_STR, VALUE_STR);
    }

    private Set<String> addSubItemsToSet(JSONArray jsonArray, Set<String> prevFound, String item, String subItem) {
        for (Object obj : jsonArray) {
            final JSONObject jsonObject = (JSONObject) obj;
            //noinspection unchecked
            final JSONArray itemList = (JSONArray) jsonObject.getOrDefault(item, null);

            if (itemList != null) {
                for (Object subItemObj : itemList) {
                    final JSONObject jsonAbility = (JSONObject) subItemObj;
                    //noinspection unchecked
                    prevFound.add(jsonAbility.getOrDefault(subItem, NULL_STR).toString());
                }
            }
        }

        return prevFound;
    }

    private void outputSet(Set<String> set) {
        final List<String> list = new ArrayList<>(set);
        outputList(list);
    }

    private void outputList(List<String> list) {
        Collections.sort(list);

        for (Object obj : list) {
            System.out.println("\t- " + obj.toString());
        }
    }

    private JSONArray getJsonList(String resourceLocation) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();

        final ClassLoader classLoader = getClass().getClassLoader();
        //noinspection ConstantConditions
        final File file = new File(classLoader.getResource(resourceLocation).getFile());
        // BugError: Did not find a way to solve.
        final Object obj = parser.parse(new FileReader(file));

        return (JSONArray) obj;
    }
}
