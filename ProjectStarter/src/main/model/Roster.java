package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

// represents a list of EnneagramCharacter, with a max of 3 characters
// provides methods to add, remove, and view characters in the roster
public class Roster implements Writable {
    private List<EnneagramCharacter> roster;

    // EFFECTS: creates a roster that holds a list of max 2 EnneagramCharacter
    public Roster() {
        roster = new ArrayList<>();
    }

    public List<EnneagramCharacter> getRoster() {
        return this.roster;
    }

    // EFFECT: returns the character at given index of roster (0 or 1)
    public EnneagramCharacter getCharacter(int index) {
        return roster.get(index);
    }

    // EFFECTS: Sets the list of EnneagramCharacter in the roster
    public void setRoster(List<EnneagramCharacter> roster) {
        this.roster = roster;
    }
    
    // REQUIRES: roster size < 2
    // MODIFIES: this
    // EFFECTS: adds the EnneagramCharacter to the roster if the roster is not full
    public boolean addCharacter(EnneagramCharacter character) {
        if (roster.size() < 2) {
            roster.add(character);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: characters > 0
    // MODIFIES: this
    // EFFECTS: removes an EnneagramCharacter from the roster if exists
    public boolean removeCharacter(EnneagramCharacter character) {
        if (roster.size() > 0) {
            roster.remove(character);
            return true;
        }
        return false;
    }


    // EFFECTS: returns a list of strings representing the names and descriptions of characters in the roster
    public List<String> viewRoster() {
        List<String> lineup = new ArrayList<>();

        if (roster.isEmpty()) { 
            // empty lineup
            return lineup;
        } 

        for (EnneagramCharacter character : roster) {
            lineup.add(character.getName() + ": " + character.getDesc());
        }

        return lineup;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("character roster", rosterToJson());

        return json;
    }

    // EFFECTS: returns roster as a JSON array
    private JSONArray rosterToJson() {
        JSONArray jsonArray = new JSONArray();

        for (EnneagramCharacter character : roster) {
            jsonArray.put(character.toJson());
        }

        return jsonArray;
    }
}
