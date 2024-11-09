package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Ability;
import model.EnneagramCharacter;
import model.Item;
import model.Roster;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// major inspiration taken from JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    private Roster roster;
    private EnneagramCharacter character;

    @BeforeEach
    void runBefore() {
        roster = new Roster();
        character = new EnneagramCharacter("enneagram character 1",
                "a basic enneagram character", 10, 20, 30,
                new Ability("Righteous Fury", "Deals 20 damage and heals for 10", 20, 15));
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEnneagramCharacterEmptyInventory() {
        try {
            roster.addCharacter(character);
            writeRosterToFile("./data/testWriterEnneagramCharacterEmptyInventory.json");

            roster = readRosterFromFile("./data/testWriterEnneagramCharacterEmptyInventory.json");
            verifyCharacterProperties(character);
            assertEquals(0, character.getInventory().size());
            // checkRoster(roster);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralEnneagramCharacter() {
        try {
            character.addItem(new Item("berry", "heals for 20 health", 20));
            character.addItem(new Item("apple", "heals for 10 health", 10));
            roster.addCharacter(character);

            writeRosterToFile("./data/testWriterGeneralEnneagramCharacter.json");
            roster = readRosterFromFile("./data/testWriterGeneralEnneagramCharacter.json");

            verifyCharacterProperties(character);
            verifyInventoryItems(character.getInventory());
            // checkRoster(roster);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void writeRosterToFile(String filePath) throws IOException {
        JsonWriter writer = new JsonWriter(filePath);
        writer.open();
        writer.write(roster);
        writer.close();
    }

    private Roster readRosterFromFile(String filePath) throws IOException {
        JsonReader reader = new JsonReader(filePath);
        return reader.read();
    }

    private void verifyCharacterProperties(EnneagramCharacter character) {
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

        checkEnneagramCharacter(
                "enneagram character 1",
                "a basic enneagram character",
                10,
                20,
                30,
                character.getAbility(),
                character.getInventory(),
                character);

        checkAbility("Righteous Fury", "Deals 20 damage and heals for 10", 20, 15, ability);
    }

    private void verifyInventoryItems(List<Item> inventory) {
        assertEquals(2, inventory.size());
        checkItem("berry", "heals for 20 health", 20, inventory.get(0));
        checkItem("apple", "heals for 10 health", 10, inventory.get(1));
    }
}