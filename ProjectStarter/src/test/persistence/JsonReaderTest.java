package persistence;

import org.junit.jupiter.api.Test;

import model.Ability;
import model.EnneagramCharacter;
import model.Item;
import model.Roster;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// major inspiration taken from JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Roster roster = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try {
            Roster roster = reader.read();
            EnneagramCharacter character = roster.getCharacter(0);

            assertEquals("enneagram character 1", character.getName());
            assertEquals("a basic enneagram character", character.getDesc());
            assertEquals(10, character.getHealth());
            assertEquals(20, character.getEnergy());
            assertEquals(30, character.getAttack());

            Ability ability = character.getAbility();
            assertNotNull(ability);
            assertEquals("Righteous Fury", ability.getName());
            assertEquals(20, ability.getDmg());
            assertEquals(15, ability.getEnergyCost());

            assertEquals(0, character.getInventory().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralEnneagramCharacter() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEnneagramCharacter.json");
        try {
            Roster roster = reader.read();
            EnneagramCharacter character = roster.getCharacter(0);

            assertEquals("enneagram character 1", character.getName());
            assertEquals("a basic enneagram character", character.getDesc());
            assertEquals(10, character.getHealth());
            assertEquals(20, character.getEnergy());
            assertEquals(30, character.getAttack());

            Ability ability = character.getAbility();
            assertEquals(ability, character.getAbility());

            List<Item> inventory = character.getInventory();
            assertEquals(2, inventory.size());
            checkItem("berry", "heals for 20 health", 20, inventory.get(0));
            checkItem("apple", "heals for 10 health", 10, inventory.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}