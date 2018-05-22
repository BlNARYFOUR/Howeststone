package setupDb;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DisplayScreen {
    public static void main(String[] args) {
        new DisplayScreen().run();
    }

    private static final String SPELLS_LOCATION = "json/spells.json";
    private static final String MINIONS_LOCATION = "json/minions.json";
    private static final String WEAPONS_LOCATION = "json/weapons.json";

    private void run() {
        try {
            Set<String> abilities = new HashSet<>();

            JSONArray spellList = getJSONList(SPELLS_LOCATION);
            JSONArray minionList = getJSONList(MINIONS_LOCATION);
            JSONArray weaponList = getJSONList(WEAPONS_LOCATION);

            addAbilitiesToSet(spellList, abilities);
            addAbilitiesToSet(minionList, abilities);
            addAbilitiesToSet(weaponList, abilities);

            outputSet(abilities);

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
}
