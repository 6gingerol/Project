package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    private Item i1;
    private Item i2;
    private List<Item> inventory; 
    private EnneagramCharacter c1;
    private Ability a1;
    

    @BeforeEach
    void runBefore() {
        inventory = new ArrayList<>(); 
        i1 = new Item("Yummy Banana", "increases current health by 10", 10);
        i2 = new Item("Scrumptious Borcht", "increases current health by 20", 20); 

        c1 = new EnneagramCharacter("The Individualist", "Expressive, Dramatic, Self-Absorbed, and Temperamental", 
        100, 100, 10, a1);
                
        a1 = new Ability("Smack", "A basic attack", 10, 5);
    }   

    @Test
    void testItemConstructor() {
        assertEquals("Yummy Banana", i1.getName());
        assertEquals("increases current health by 10", i1.getDesc());
        assertEquals(10, i1.getEffect());
    }

}