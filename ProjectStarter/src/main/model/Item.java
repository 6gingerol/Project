package model;

import org.json.JSONObject;

import persistence.Writable;

// represents an item that can be used for stat boosts (ONLY HEALING ATM)
// each has a name, a description of what it does and an effect value
public class Item implements Writable {
    private String name; 
    private String desc; // eg; "heals for __"
    private int effect; // 100 
    // add type field if wanna do more than heal...

    // EFFECTS: creates an Item with a name, 
    // a description of what it does (desc), and an int for effect/what it does (effect)
    public Item(String name, String desc, int effect) {
        this.name = name; 
        this.desc = desc;
        this.effect = effect; 
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getEffect() {
        return this.effect; 
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", desc);
        json.put("effect", effect);  
        
        return json;
    }

}

    

