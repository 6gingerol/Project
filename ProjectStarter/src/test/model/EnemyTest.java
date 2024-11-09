package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyTest {
    private Enemy e1;
    private EnneagramCharacter c1;
    private Ability a4;

    @BeforeEach
    void runBefore() {
        a4 = new Ability("Creative Outburst", "Deals 30 damage and stuns for 1 turn", 30, 20);
        e1 = new Enemy("Big Boss", 100, 10);
        c1 = new EnneagramCharacter("The Individualist", "Expressive, Dramatic, Self-Absorbed, and Temperamental",
                100, 100, 10, a4);
    }

    @Test
    public void testConstructor() {
        assertEquals("Big Boss", e1.getName());
        assertEquals(100, e1.getHealth());
        assertEquals(10, e1.getDmg());
    }

    @Test
    public void testGetters() {
        assertEquals("Big Boss", e1.getName());
        assertEquals(100, e1.getHealth());
        assertEquals(10, e1.getDmg());
    }

    @Test
    public void testSetters() {
        e1.setName("Orc"); // TODO: fix later
        e1.setHealth(15);
        e1.setDmg(25);
        assertEquals("Orc", e1.getName());
        assertEquals(15, e1.getHealth());
        assertEquals(25, e1.getDmg());
    }

    @Test
    public void testAttack() {
        assertEquals(100, c1.getHealth());
        e1.attack(c1);
        assertEquals(90, c1.getHealth());

        EnneagramCharacter almostDead = new EnneagramCharacter("The Individualist",
                "Expressive, Dramatic, Self-Absorbed, and Temperamental", 5, 100, 10, a4);
        assertEquals(5, almostDead.getHealth());
        e1.attack(almostDead);
        assertEquals(0, almostDead.getHealth());

        e1.attack(almostDead);
        assertEquals(0, almostDead.getHealth());
    }

    @Test
    public void testIsEnemyAlive() {
        assertTrue(e1.isEnemyAlive());
        e1.setHealth(0);
        assertFalse(e1.isEnemyAlive());
    }

    @Test
    public void testEnemyNotAlive() {
        e1.setHealth(0);
        assertFalse(e1.isEnemyAlive());
    }
}