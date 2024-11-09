package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

// Represents a character based on an Enneagram type
// each has a type, stats (health and energy), attack, ability and an inventory
public class EnneagramCharacter implements Writable {
    private String name;
    private String desc;
    private int health;
    private int energy;
    private int attack;
    private Ability ability; // gonna make ability class later
    private List<Item> inventory;

    // REQUIRES:
    // EFFECTS: creates an EnneagramCharacter with a name, description, energy, attack, ability
    public EnneagramCharacter(String name, String desc, int health, int energy, int attack, Ability ability) {
        this.name = name;
        this.desc = desc;
        this.health = health;
        this.energy = energy;
        this.attack = attack;
        this.ability = ability; // starts w/o ability?
        inventory = new ArrayList<>(); // empty inventory
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAttack() {
        return this.attack;
    }

    public Ability getAbility() {
        return this.ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability; 
    }

    public List<Item> getInventory() {
        return inventory;
    }

    // MODIFIES: this
    // EFFECTS: adds item into inventory
    public void addItem(Item item) {
        this.inventory.add(item);
    }

    // REQUIRES: inventory not empty
    // MODIFIES: this
    // EFFECTS: removes item from inventory
    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    // REQUIRES: this.energy >= this.ability.getEnergyCost()
    // MODIFIES: this.energy, enemy.health
    // EFFECTS: uses character's ability on enemy, dealing damage and reducing
    // character's energy
    public int useAbility(Enemy enemy) { // probably should take in which ability to use?
        if (this.energy >= this.ability.getEnergyCost()) { // you casted X ability!
            this.energy = this.energy - this.ability.getEnergyCost(); // your energy is now X!
            enemy.setHealth(enemy.getHealth() - this.ability.getDmg());
            return this.ability.getDmg(); // you did X dmg!
        } else {
            return 0; // X ability cannot be used!
        }
    }

    // REQUIRES: enemy.health > 0
    // MODIFIES: enemy.health
    // EFFECTS: character attacks enemy, dealing damage based on character's attack
    // stat
    public void attack(Enemy enemy) {
        if (enemy.getHealth() > 0) {
            if (enemy.getHealth() <= this.attack) {
                enemy.setHealth(0);
            } else {
                enemy.setHealth(enemy.getHealth() - this.attack);
            }
        }
    } // you did this.attack dmg!
      // enemy health is now X!
      // maybe later change to check for character health to not attck?

    public List<String> viewInventory() {
        List<String> inventoryList = new ArrayList<>();
        if (inventory.isEmpty()) {
            return inventoryList;
        } else {
            for (Item item : this.inventory) {
                inventoryList.add(item.getName() + ": " + item.getDesc());
            }
            return inventoryList;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", desc);
        json.put("health", health);
        json.put("energy", energy);
        json.put("attack", attack);
        json.put("ability", ability.toJson());
        json.put("inventory", inventoryToJson());

        return json;
    }

    // EFFECTS: returns inventory in this EnneagramCharacter as a JSON array
    private JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : inventory) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
