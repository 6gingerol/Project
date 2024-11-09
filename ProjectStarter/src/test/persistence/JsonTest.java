package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import model.Ability;
import model.EnneagramCharacter;
import model.Item;
import model.Roster;

// major inspiration taken from JsonSerializationDemo

public class JsonTest {
    protected void checkEnneagramCharacter(String name, String desc, int health, int energyCost, int attack,
            Ability ability,
            List<Item> inventory, EnneagramCharacter character) {
        assertEquals(name, character.getName());
        assertEquals(desc, character.getDesc());
        assertEquals(health, character.getHealth());
        assertEquals(energyCost, character.getEnergy());
        assertEquals(attack, character.getAttack());
        assertEquals(ability, character.getAbility());
        assertEquals(inventory, character.getInventory());
    }

    protected void checkItem(String name, String desc, int effect, Item item) {
        assertEquals(name, item.getName());
        assertEquals(desc, item.getDesc());
        assertEquals(effect, item.getEffect());
    }

    protected void checkAbility(String name, String desc, int dmg, int energyCost, Ability ability) {
        assertEquals(name, ability.getName());
        assertEquals(desc, ability.getDesc());
        assertEquals(dmg, ability.getDmg());
        assertEquals(energyCost, ability.getEnergyCost());
    }

    // protected void checkRoster(Roster roster) {
    //     assertEquals(1, roster.getRoster().size(), "Roster size does not match expected size of 1.");
    
    //     EnneagramCharacter actualCharacter = roster.getRoster().get(0);
        
    //     // Check essential properties of the character
    //     assertEquals(character.getName(), actualCharacter.getName(), "Character names do not match.");
    //     assertEquals(character.getHealth(), actualCharacter.getHealth(), "Character health does not match.");
    //     assertEquals(character.getEnergy(), actualCharacter.getEnergy(), "Character energy does not match.");
    //     assertEquals(character.getAttack(), actualCharacter.getAttack(), "Character attack does not match.");
    // }
}
