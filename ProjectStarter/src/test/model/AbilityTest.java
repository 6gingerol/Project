package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbilityTest {

    
    private Ability a1;

    @BeforeEach
    void runBefore() {
        a1 = new Ability("Righteous Fury", "Deals 20 damage and heals for 10", 20, 15);
    }

    @Test
    void testConstructor() {
        assertEquals("Righteous Fury", a1.getName());
        assertEquals("Deals 20 damage and heals for 10", a1.getDesc());
        assertEquals(20, a1.getDmg());
        assertEquals(15, a1.getEnergyCost());
    }

    @Test
    void testSetName() {
        a1.setName("New Name");
        assertEquals("New Name", a1.getName());
    }

    @Test
    void testSetDesc() {
        a1.setDesc("New Description");
        assertEquals("New Description", a1.getDesc());
    }

    @Test
    void testSetDmg() {
        a1.setDmg(25);
        assertEquals(25, a1.getDmg());
    }

    @Test
    void testSetEnergyCost() {
        a1.setEnergyCost(20);
        assertEquals(20, a1.getEnergyCost());
    }

    @Test
    void testMultipleAbilities() {
    }
}
