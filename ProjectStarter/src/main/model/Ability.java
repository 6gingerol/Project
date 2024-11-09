package model;

import org.json.JSONObject;

import persistence.Writable;

// represents an Ability with a name, description, damage it does, and energy cost
// Abilities are used by characters to deal damage to enemies
public class Ability implements Writable {
    private String name;
    private String desc;
    private int dmg;
    private int energyCost;

    // represents an Ability with a name, description, damage it does, and energy
    // cost to use
    public Ability(String name, String desc, int dmg, int energyCost) {
        this.name = name;
        this.desc = desc;
        this.dmg = dmg;
        this.energyCost = energyCost;
        // making specific abilities for characters
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDmg() {
        return dmg;
    }

    // MODIFIES: this
    // EFFECTS: sets dmg
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    // MODIFIES: this
    // EFFECTS: sets energycost
    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    // PHASE 2:

    // private String name;
    // private String desc;
    // private int dmg;
    // private int energyCost;


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", desc);
        json.put("damage", dmg); 
        json.put("energy cost", energyCost); 
        
        return json;
    }
}
