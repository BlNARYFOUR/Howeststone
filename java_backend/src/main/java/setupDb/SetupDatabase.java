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

    private static final String[] HEROES = {"Mage", "Paladin"};

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

        System.out.println("Mechanics:");
        outputSet(mechanics);

        System.out.println("\nAdding mechanics to database...");
        List<String> mechanicList = new ArrayList<>(mechanics);
        Collections.sort(mechanicList);
        for (String mechanic : mechanicList) {
            insertMechanic(mechanic);
        }
        System.out.println(ColorFormats.blue("mechanics have been added!"));
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

    private Set<String> addAbilitiesToSet(JSONArray jsonArray, Set<String> prevFoundAbilities) {
        return addSubItemsToSet(jsonArray, prevFoundAbilities, "abilities", "name");
    }

    private Set<String> addMechanicTypeToSet(JSONArray jsonArray, Set<String> prevFoundMechanicTypes) {
        return addSubItemsToSet(jsonArray, prevFoundMechanicTypes, "mechanics", "type");
    }

    private Set<String> addSubItemsToSet(JSONArray jsonArray, Set<String> prevFound, String item, String subItem) {
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            //noinspection unchecked
            JSONArray abilityList = (JSONArray) jsonObject.getOrDefault(item, null);

            if(abilityList != null) {
                for (Object ability : abilityList) {
                    JSONObject jsonAbility = (JSONObject) ability;
                    prevFound.add(jsonAbility.get(subItem).toString());
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
