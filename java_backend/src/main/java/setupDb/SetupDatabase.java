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

    private SqlDatabase db = new SqlDatabase("jdbc:mysql://localhost:3306/howeststone", "root", "");
    //private static final SqlDatabase db = null;

    private void run() {
        try {
            Set<String> abilities = new HashSet<>();

            JSONArray spellList = getJSONList(SPELLS_LOCATION);
            JSONArray minionList = getJSONList(MINIONS_LOCATION);
            JSONArray weaponList = getJSONList(WEAPONS_LOCATION);

            addAbilitiesToSet(spellList, abilities);
            addAbilitiesToSet(minionList, abilities);
            addAbilitiesToSet(weaponList, abilities);

            System.out.println("Abilities:");
            outputSet(abilities);

            System.out.println("Adding abilities to database...");
            List<String> abilityList = new ArrayList<>(abilities);
            Collections.sort(abilityList);
            for (String ability : abilityList) {    
                insertAbility(ability);
            }
            System.out.println(ColorFormats.blue("abilities have been added!"));

        } catch (ParseException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Set<String> addAbilitiesToSet(JSONArray jsonArray, Set<String> prevFoundAbilities) {
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            //noinspection unchecked
            JSONArray abilityList = (JSONArray) jsonObject.getOrDefault("abilities", null);

            if(abilityList != null) {
                for (Object ability : abilityList) {
                    JSONObject jsonAbility = (JSONObject) ability;
                    prevFoundAbilities.add(jsonAbility.get("name").toString());
                }
            }
        }

        return prevFoundAbilities;
    }

    private void outputSet(Set<String> set) {
        List<String> list = new ArrayList<>(set);
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

    private void insertAbility(String ability) {
        try (
                Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SqlStatements.INSERT_ABILITY,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, ability);
            final int AFFECTED_ROWS = stmt.executeUpdate();

            if (AFFECTED_ROWS == 0) {
                throw new SQLException("No ability created: no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final long ID = rs.getLong(1);
                    System.out.printf("\t* '%s' now has ID %d\n", ability, ID);
                } else {
                    throw new SQLException("No ability created: no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
