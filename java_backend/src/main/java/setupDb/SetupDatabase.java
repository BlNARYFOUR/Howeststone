package setupDb;

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

public class SetupDatabase {
    public static void main(String[] args) {
        new SetupDatabase().run();
    }

    private static final String SPELLS_LOCATION = "json/spells.json";
    private static final String MINIONS_LOCATION = "json/minions.json";
    private static final String WEAPONS_LOCATION = "json/weapons.json";

    private static final String[] HEROES = {"Neutral", "Mage", "Paladin"};
    private static final String[] ABILITIES_WITH_MECHANICS = {"Aura", "Battlecry", "Deathrattle", "DestroyedDivineShield", "Enrage", "On Attack", "On Death", "On Heal", "On Hit", "Turn Begin", "Turn End", "Untargetable", "Update In Hand"};

    private SqlDatabase db = new SqlDatabase("jdbc:mysql://localhost:3306/howeststone", "root", "");
    private JSONArray spellList;
    private JSONArray minionList;
    private JSONArray weaponList;

    private SetupDatabase() {
        try {
            spellList = getJSONList(SPELLS_LOCATION);
            minionList = getJSONList(MINIONS_LOCATION);
            weaponList = getJSONList(WEAPONS_LOCATION);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        addAbilitiesToDb();
        addHeroesToDb();
        addMechanicsToDb();
        listAllMechanicTargets();
        listAllMechanicValues();
        addCardsToDb();
    }

    private void addAbilitiesToDb() {
        Set<String> abilities = new HashSet<>();

        addAbilitiesToSet(spellList, abilities);
        addAbilitiesToSet(minionList, abilities);
        addAbilitiesToSet(weaponList, abilities);

        System.out.println("Abilities:");
        outputSet(abilities);

        System.out.println("\nAdding abilities to database...");
        List<String> abilityList = new ArrayList<>(abilities);
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
        Set<String> mechanics = new HashSet<>();

        addMechanicTypeToSet(spellList, mechanics);
        addMechanicTypeToSet(weaponList, mechanics);
        addMechanicTypeToSet(minionList, mechanics);

        System.out.println("\nMechanics:");
        outputSet(mechanics);

        System.out.println("\nAdding mechanics to database...");
        List<String> mechanicList = new ArrayList<>(mechanics);
        Collections.sort(mechanicList);
        for (String mechanic : mechanicList) {
            insertMechanic(mechanic);
        }
        System.out.println(ColorFormats.blue("mechanics have been added!"));
    }

    private void addCardsToDb() {
        System.out.println("\nAdding cards...");
        addCardFromJSONArray(spellList);
        addCardFromJSONArray(minionList);
        addCardFromJSONArray(weaponList);
        System.out.println(ColorFormats.blue("cards have been added!"));
    }

    private void addCardFromJSONArray(JSONArray cards) {
        for (Object card : cards) {
            JSONObject jsonCard = (JSONObject) card;

            String name;
            String type;
            String race;
            String img;
            String rarity;
            int attack;
            int health;
            int manaCost;
            int durability;
            int heroId = 0;

            //noinspection unchecked
            name = jsonCard.getOrDefault("name", "NULL").toString();
            //noinspection unchecked
            type = jsonCard.getOrDefault("type", "NULL").toString();
            //noinspection unchecked
            race = jsonCard.getOrDefault("race", "NULL").toString();
            //noinspection unchecked
            img = jsonCard.getOrDefault("img", "NULL").toString();
            //noinspection unchecked
            rarity = jsonCard.getOrDefault("rarity", "NULL").toString();
            //noinspection unchecked
            attack = Integer.parseInt(jsonCard.getOrDefault("attack", 0).toString());
            //noinspection unchecked
            health = Integer.parseInt(jsonCard.getOrDefault("health", 0).toString());
            //noinspection unchecked
            manaCost = Integer.parseInt(jsonCard.getOrDefault("cost", 0).toString());
            //noinspection unchecked
            durability = Integer.parseInt(jsonCard.getOrDefault("durability", 0).toString());
            //noinspection unchecked
            heroId = getHeroId(jsonCard.getOrDefault("playerClass", "NULL").toString());

            if(0 <= heroId) {
                int cardId = insertCard(name, type, race, img, rarity, attack, health, manaCost, durability, heroId);
                addCardAbilityToDb(jsonCard, cardId);
            }
        }
    }

    private void addCardAbilityToDb(JSONObject card, int cardId) {
        //noinspection unchecked
        JSONArray jsonAbilities = (JSONArray) card.getOrDefault("abilities", null);

        if(jsonAbilities != null) {
            for (Object subItemObj : jsonAbilities) {
                JSONObject jsonAbility = (JSONObject) subItemObj;
                //noinspection unchecked
                String abilityName = jsonAbility.get("name").toString();

                int abilityId = getAbilityId(abilityName);

                if (0 <= abilityId) {
                    int cardMechId = 1;

                    if(Arrays.asList(ABILITIES_WITH_MECHANICS).contains(abilityName)) {
                        //noinspection unchecked
                        JSONArray jsonMechanics = (JSONArray) card.getOrDefault("mechanics", null);
                        //noinspection unchecked
                        int mechNeeded = Integer.parseInt(jsonAbility.getOrDefault("mechNeeded", jsonMechanics.size()).toString());

                        for (int i=0; i<mechNeeded; i++) {
                            Object obj = jsonMechanics.get(i);

                            JSONObject jsonMechanic = (JSONObject) obj;
                            //noinspection unchecked
                            String mechanicType = jsonMechanic.get("type").toString();
                            //noinspection unchecked
                            String target = jsonMechanic.getOrDefault("target", "NULL").toString();
                            //noinspection unchecked
                            String value = jsonMechanic.getOrDefault("value", "NULL").toString();

                            int mechanicId = getMechanicId(mechanicType);

                            if (0 <= mechanicId) {
                                cardMechId = insertCardMechanic(mechanicId, target, value);
                                insertCardAbility(abilityId, cardId, cardMechId);
                            }
                        }
                    } else {
                        insertCardAbility(abilityId, cardId, cardMechId);
                    }
                }
            }
        }
    }

    private void listAllMechanicTargets() {
        Set<String> mechanics = new HashSet<>();

        addMechanicTargetToSet(spellList, mechanics);
        addMechanicTargetToSet(weaponList, mechanics);
        addMechanicTargetToSet(minionList, mechanics);

        System.out.println("\nTargets:");
        outputSet(mechanics);
    }

    private void listAllMechanicValues() {
        Set<String> mechanics = new HashSet<>();

        addMechanicValueToSet(spellList, mechanics);
        addMechanicValueToSet(weaponList, mechanics);
        addMechanicValueToSet(minionList, mechanics);

        System.out.println("\nValues:");
        outputSet(mechanics);
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

    private int insertCard(String name, String type,String race ,String img, String rarity, int attack, int health, int manaCost, int durability, int heroId) {
        long id = -1;

        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, race);
            stmt.setString(4, img);
            stmt.setString(5, rarity);
            stmt.setInt(6, attack);
            stmt.setInt(7, health);
            stmt.setInt(8, manaCost);
            stmt.setInt(9, durability);
            stmt.setInt(10, heroId);
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
                throw new SQLException("No card created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                    System.out.printf("\t* '%s' now has ID %d\n", name, id);
                } else {
                    throw new SQLException("No card created: no ID obtained.");
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
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
                throw new SQLException("No " + name + " created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final long ID = rs.getLong(1);
                    System.out.printf("\t* '%s' now has ID %d\n", value, ID);
                } else {
                    throw new SQLException("No " + name + " created: no ID obtained.");
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
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
                throw new SQLException("No cardMechanic created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cardMechId = rs.getLong(1);
                    System.out.printf("\t* new cardMechanic now has ID %d\n", cardMechId);
                } else {
                    throw new SQLException("No cardMechanic created: no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) cardMechId;
    }

    private void insertCardAbility(int abilityId, int cardId, int cardMechId) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_CARD_ABILITY)
        ) {
            stmt.setInt(1, abilityId);
            stmt.setInt(2, cardId);
            stmt.setInt(3, cardMechId);
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
                throw new SQLException("No cardAbility created: no rows affected.");
            } else {
                System.out.println("\t* new cardAbility has been added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        ){
            stmt.setString(1, value);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    num = rs.getInt(name);
                } else {
                    System.out.println(ColorFormats.red("\t* No " + name + " found for "+ value +"!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    private Set<String> addAbilitiesToSet(JSONArray jsonArray, Set<String> prevFoundAbilities) {
        return addSubItemsToSet(jsonArray, prevFoundAbilities, "abilities", "name");
    }

    private Set<String> addMechanicTypeToSet(JSONArray jsonArray, Set<String> prevFoundMechanicTypes) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicTypes, "mechanics", "type");
    }

    private Set<String> addMechanicTargetToSet(JSONArray jsonArray, Set<String> prevFoundMechanicTargets) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicTargets, "mechanics", "target");
    }

    private Set<String> addMechanicValueToSet(JSONArray jsonArray, Set<String> prevFoundMechanicValues) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicValues, "mechanics", "value");
    }

    private Set<String> addSubItemsToSet(JSONArray jsonArray, Set<String> prevFound, String item, String subItem) {
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            //noinspection unchecked
            JSONArray itemList = (JSONArray) jsonObject.getOrDefault(item, null);

            if(itemList != null) {
                for (Object subItemObj : itemList) {
                    JSONObject jsonAbility = (JSONObject) subItemObj;
                    //noinspection unchecked
                    prevFound.add(jsonAbility.getOrDefault(subItem, "NULL").toString());
                }
            }
        }

        return prevFound;
    }

    private void outputSet(Set<String> set) {
        List<String> list = new ArrayList<>(set);
        outputList(list);
    }

    private void outputList(List<String> list) {
        Collections.sort(list);

        for (Object obj : list) {
            System.out.println("\t- " + obj.toString());
        }
    }

    private JSONArray getJSONList(String resourceLocation) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        //noinspection ConstantConditions
        File file = new File(classLoader.getResource(resourceLocation).getFile());
        Object obj = parser.parse(new FileReader(file));

        return (JSONArray) obj;
    }
}