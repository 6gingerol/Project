package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Ability;
import model.EnneagramCharacter;
import model.Item;
import model.Roster;

// major inspiration taken from JsonSerializationDemo

// Represents a reader that reads Roster from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads roster from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Roster read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRoster(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses roster from JSON object and returns it
    private Roster parseRoster(JSONObject jsonObject) {
        Roster roster = new Roster();
        addCharacters(roster, jsonObject);

        return roster;
    }

    // MODIFIES: roster
    // EFFECTS: parses characters from JSON object and adds them to roster
    private void addCharacters(Roster roster, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("character roster");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            parseEnneagramCharacter(roster, nextItem);
        }
    }
    
    // MODIFIES: roster
    // EFFECTS: parses enneagram character from JSON object and adds it to roster
    private EnneagramCharacter parseEnneagramCharacter(Roster roster, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String desc = jsonObject.getString("description");
        int health = jsonObject.getInt("health");
        int energy = jsonObject.getInt("energy");
        int attack = jsonObject.getInt("attack");

        EnneagramCharacter character = new EnneagramCharacter(name, desc, health, energy, attack, null);
        addAbility(character, jsonObject);
        addItems(character, jsonObject);

        roster.addCharacter(character); 

        return character; 
    }


    // MODIFIES: eChar
    // EFFECTS: parses ability from JSON object and adds it to EnneagramCharacter
    private void addAbility(EnneagramCharacter character, JSONObject jsonObject) {
        JSONObject abilityJson = jsonObject.getJSONObject("ability");
        String name = abilityJson.getString("name");
        String desc = abilityJson.getString("description");
        int dmg = abilityJson.getInt("damage");
        int energyCost = abilityJson.getInt("energy cost");

        Ability ability = new Ability(name, desc, dmg, energyCost);
        character.setAbility(ability);
    }

    // MODIFIES: eChar
    // EFFECTS: parses items from JSON object and adds them to EnneagramCharacter
    private void addItems(EnneagramCharacter character, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(character, nextItem);
        }
    }

    // MODIFIES: eChar
    // EFFECTS: parses item from JSON object and adds it to EnneagramCharacter
    private void addItem(EnneagramCharacter character, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String desc = jsonObject.getString("description");
        int effect = jsonObject.getInt("effect");
        Item item = new Item(name, desc, effect);
        character.addItem(item);
    }
}
