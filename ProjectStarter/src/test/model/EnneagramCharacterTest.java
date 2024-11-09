package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EnneagramCharacterTest {
    
    private EnneagramCharacter c1;
    private Ability a1;
    private Item i1; 
    private Item i2;
    private Enemy enemy1;

    @BeforeEach
    void runBefore() {
        a1 = new Ability("Smack", "A basic attack", 10, 5);

        c1 = new EnneagramCharacter("The Individualist", "Expressive, Dramatic, Self-Absorbed, and Temperamental", 
        100, 100, 10, a1); 
        


        // Item Class stuff
        i1 = new Item("Yummy Banana", "increases current health by 10", 10);
        i2 = new Item("Scrumptious Borcht", "increases current health by 20", 20); 

        enemy1 = new Enemy("Big Boss", 100, 10);
    }
    
    @Test
    void testSetEnergy() {
        c1.setEnergy(50);
        assertEquals(50, c1.getEnergy());
    }

    @Test
    void testGetAttack() {
        assertEquals(10, c1.getAttack());
    }

    @Test
    void testGetAbility() {
        assertEquals(a1, c1.getAbility());
    }

    @Test
    void testCharConstructor() {
        assertEquals("The Individualist", c1.getName());
        assertEquals("Expressive, Dramatic, Self-Absorbed, and Temperamental", c1.getDesc());
        assertEquals(100, c1.getHealth());
        assertEquals(100, c1.getEnergy());
        // assertEquals(a1, c1.getAbility());
        assertEquals(0, c1.getInventory().size());
    }

    @Test
    void testaddItem() {
        c1.addItem(i1);
        assertTrue(c1.getInventory().contains(i1));
    }

    @Test
    void testaddMultipleItem() {
        c1.addItem(i1);
        c1.addItem(i2);
        assertTrue(c1.getInventory().contains(i1));
        assertTrue(c1.getInventory().contains(i2));
    }
    
    @Test
    void testRemoveItem() {
        c1.addItem(i1);
        c1.removeItem(i1);
        assertFalse(c1.getInventory().contains(i1));
    }

    @Test
    void testremoveMultipleItem() {
        c1.addItem(i1);
        c1.addItem(i2);
        c1.removeItem(i1);
        assertFalse(c1.getInventory().contains(i1));
        assertTrue(c1.getInventory().contains(i2));
        c1.removeItem(i2);
        assertFalse(c1.getInventory().contains(i1));
        assertFalse(c1.getInventory().contains(i2));
    }
    
    @Test
    void testUseAbility() { // make ability defined when making 
        c1.useAbility(enemy1);
        assertEquals(90, enemy1.getHealth());
        assertEquals(95, c1.getEnergy());
    }

    @Test
    void testUseAbilityNotEnoughEnergy() {
        c1.setEnergy(4); // not enough energy to use the ability
        assertEquals(0, c1.useAbility(enemy1));
        assertEquals(4, c1.getEnergy()); // energy should not change
        assertEquals(100, enemy1.getHealth()); // enemy's health should not change
    }

    @Test
    void testAttack() {
        Enemy e1 = new Enemy("Big Boss", 100, 10);
        assertEquals(100, e1.getHealth());
        c1.attack(e1);
        assertEquals(90, e1.getHealth());

        Enemy low = new Enemy("Big Boss", 5, 10);
        assertEquals(5, low.getHealth());
        c1.attack(low);
        assertEquals(0, low.getHealth());

        c1.attack(low);
        assertEquals(0, low.getHealth());
    }

    @Test
    void testViewInventory() {
        c1.addItem(i1);
        c1.addItem(i2);
        List<String> inventoryList = c1.viewInventory();
        assertTrue(inventoryList.contains(i1.getName() + ": " + i1.getDesc()));
        assertTrue(inventoryList.contains(i2.getName() + ": " + i2.getDesc()));
    }

    @Test
    void testViewEmptyInventory() {
        List<String> inventoryList = c1.viewInventory();
        assertEquals(0, inventoryList.size());
    }
}