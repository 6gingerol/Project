package ui;

import java.util.ArrayList;
import java.util.List;

import model.Ability;
import model.EnneagramCharacter;

// represents a database of enneagramcharacters and their respective abilities
public class CharacterDatabase {
    List<EnneagramCharacter> characterDatabase;
    EnneagramCharacter c1;
    EnneagramCharacter c2;
    EnneagramCharacter c3;
    EnneagramCharacter c4;
    EnneagramCharacter c5;

    public CharacterDatabase() {
        characterDatabase = new ArrayList<>();
    }

    public void loadReformer() {
        c1 = new EnneagramCharacter("The Reformer",
                "Principled, Purposeful, and Perfectionistic",
                100, 100, 10, new Ability("Righteous Fury",
                        "Deals 20 damage and heals for 10", 20, 15));
        characterDatabase.add(c1);
    }

    public void loadHelper() {
        c2 = new EnneagramCharacter("The Helper",
                "Generous, Caring, and People-Pleasing",
                90, 85, 20, new Ability("Healing Touch", "Heals for 25", 0, 10));
        characterDatabase.add(c2);
    }

    public void loadAchiever() {
        c3 = new EnneagramCharacter("The Achiever",
                "Success-Oriented, Pragmatic, and Adaptive",
                95, 90, 15, new Ability("Motivational Speech", "Increases attack by 10 for 2 turns", 0, 5));
        characterDatabase.add(c3);
    }

    public void loadIndividualist() {
        c4 = new EnneagramCharacter("The Individualist",
                "Expressive, Self-Absorbed, and Temperamental",
                100, 100, 10, new Ability("Creative Outburst", "Deals 30 damage and stuns for 1 turn", 30, 20));
        characterDatabase.add(c4);

    }

    public void loadInvestigator() {
        c5 = new EnneagramCharacter("The Investigator",
                "Analytical, Independent, and Innovative",
                85, 95, 12, new Ability("Insightful Analysis", "Increase damage by 20% for 1 turn", 0, 10));
        characterDatabase.add(c5);
    }

    public void loadCharacters() {
        loadReformer();
        loadHelper();
        loadAchiever();
        loadIndividualist();
        loadInvestigator();
    }

    public void displayCharacterDatabase() {
        for (int i = 0; i < characterDatabase.size(); i++) {
            EnneagramCharacter character = characterDatabase.get(i);
            System.out.println((i + 1) + ". " + character.getName());
        }
    }
}
