package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RosterTest {

    private Roster roster;
    private EnneagramCharacter c1;
    private EnneagramCharacter c2;
    private EnneagramCharacter c3;
    private List<String> lineup;
    private Ability a1;
    private Ability a2;
    private Ability a3;

    @BeforeEach
    void runBefore() {
        roster = new Roster();
        
        a1 = new Ability("Righteous Fury", 
        "Deals 20 damage and heals for 10", 20, 15);
        a2 = new Ability("Healing Touch", "Heals for 25", 0, 10);
        a3 = new Ability("Motivational Speech", "Increases attack by 10 for 2 turns", 0, 5);

        c1 = new EnneagramCharacter("The Individualist", "Expressive, Dramatic, Self-Absorbed, and Temperamental", 
        100, 100, 10, a1);
        c2 = new EnneagramCharacter("The Helper", "Generous, Caring, Empathetic, and People-Pleasing", 
        90, 85, 20, a2);
        c3 = new EnneagramCharacter("The Achiever", "Success-Oriented, Pragmatic, and Adaptive", 
        95, 90, 15, a3);

    }

    @Test
    void testRosterConstructor() {
        assertEquals(0, roster.getRoster().size());
    }

    @Test
    void testSetRoster() {
        List<EnneagramCharacter> newRoster = new ArrayList<>();
        newRoster.add(c1);
        newRoster.add(c2);
        roster.setRoster(newRoster);
        assertEquals(newRoster, roster.getRoster());
    }

    @Test
    void testAddCharacter() {
        roster.addCharacter(c1);
        assertEquals(1, roster.getRoster().size());
        assertTrue(roster.getRoster().contains(c1));
    }

    @Test
    void testAddMultipleCharacters() {
        roster.addCharacter(c1);
        roster.addCharacter(c2);
        assertEquals(2, roster.getRoster().size());
        assertTrue(roster.getRoster().contains(c1));
        assertTrue(roster.getRoster().contains(c2));
    }

    @Test
    void testAddMoreThanTwoCharacters() {
        roster.addCharacter(c1);
        roster.addCharacter(c2);
        assertFalse(roster.addCharacter(c3));
        assertEquals(2, roster.getRoster().size());
        assertTrue(roster.getRoster().contains(c1));
        assertTrue(roster.getRoster().contains(c2));
        assertFalse(roster.getRoster().contains(c3));
    }

    @Test
    void testRemoveCharacter() {
        
        assertFalse(roster.removeCharacter(c1));
        assertFalse(roster.getRoster().contains(c1));
        assertEquals(0, roster.getRoster().size());

        roster.addCharacter(c1);
        roster.addCharacter(c2);
        roster.removeCharacter(c1);
        assertTrue(roster.removeCharacter(c1));
        assertEquals(1, roster.getRoster().size());
        assertFalse(roster.getRoster().contains(c1));
        assertTrue(roster.getRoster().contains(c2));
    }

    @Test
    void testRemoveMultipleCharacters() {
        roster.addCharacter(c1);
        roster.addCharacter(c2);
        roster.removeCharacter(c1);
        roster.removeCharacter(c2);
        assertEquals(0, roster.getRoster().size());
        assertFalse(roster.getRoster().contains(c1));
        assertFalse(roster.getRoster().contains(c2));
    }

    @Test
    void testViewRoster() {
        roster.addCharacter(c1);
        roster.addCharacter(c2);
        roster.addCharacter(c3);
        lineup = roster.viewRoster();
        assertTrue(lineup.contains(c1.getName() + ": " + c1.getDesc()));
        assertTrue(lineup.contains(c2.getName() + ": " + c2.getDesc()));
    }

    @Test
    void testViewEmptyRoster() {
        lineup = roster.viewRoster();
        assertEquals(0, lineup.size());
    }
}
