package model;

// import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// represents an random enemy character
// each has a name, a health value and a damage value
public class Enemy {
    private String name;
    // private String desc; // can remove if too much work lol
    private int health;
    private int dmg;

    // EFFECTS: creates an Enemy with a name, a health value and an attack dmg value
    public Enemy(String name, int health, int dmg) {
        this.name = name;
        this.health = health;
        this.dmg = dmg;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDmg() {
        return this.dmg;
    }

    // sets name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets health
    public void setHealth(int health) {
        this.health = health;
    }

    // MODIFIES: this
    // EFFECTS: sets dmg
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    // REQUIRES: EnneagramCharacter.health > 0
    // MODIFIES: EnneagramCharacter.health
    // EFFECTS: attacks character, reducing its health by the damage value
    // (enemy.dmg)
    public void attack(EnneagramCharacter character) {
        // dmg = ThreadLocalRandom.current().nextInt(10, 30 + 1);
        if (character.getHealth() > 0) {
            if (character.getHealth() <= getDmg()) {
                character.setHealth(0);
            } else {
                character.setHealth(character.getHealth() - getDmg());
            }
        }
    }

    // REQUIRES: EnneagramCharacter.health > 0
    // MODIFIES: EnneagramCharacter.health
    // EFFECTS: returns true if enemy is alive, false otherwise
    public boolean isEnemyAlive() {
        return this.health > 0;
    }
}
