package ui;

import model.Enemy;
import model.EnneagramCharacter;
import model.Item;
import model.Roster;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

// inspiration taken from TellerApp

// represents an instance of a game of Enneagram RPG
public class GameApp {
    static Scanner scanner = new Scanner(System.in);

    private static Roster roster;
    private static CharacterDatabase characterDatabase;
    private static Enemy enemy;
    private static ItemDatabase items;

    private static final String JSON_STORE = "./data/CharacterRoster.json";
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;

    // EFFECTS: initializes the game application and certain objects
    public GameApp() {
        roster = new Roster();

        characterDatabase = new CharacterDatabase();
        characterDatabase.loadCharacters();

        items = new ItemDatabase();
        items.loadItems();

        // Initialize JsonWriter and JsonReader
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runGame();
    }

    // EFFECTS: displays the main menu options
    public static void displayMenu() {
        System.out.println("Please select an option (1 or 2)");
        System.out.println("1. Start Game");
        System.out.println("2. Quit");
        System.out.print("Enter Your Choice: ");
    }

    // EFFECTS: runs the game loop， processes user input
    private static void runGame() {
        boolean keepGoing = true;
        int choice = 0;

        while (keepGoing) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                runMain();
            } else if (choice == 2) {
                keepGoing = false;
                System.out.println("You have quit the game.");
                quit();
            } else {
                System.out.println("Invalid Choice.");
            }
        }
    }

    // EFFECTS: exits the game application
    public static void quit() {
        System.exit(0);
    }

    // EFFECTS: displays main menu
    public static void displayMainMenu() { // starts game
        System.out.println("Welcome to Main Menu. Please select an option (1, 2, 3 or 4)");
        System.out.println("1. Battle");
        System.out.println("2. Edit Roster");
        System.out.println("3. Edit Items");
        System.out.println("4. Quit");
        System.out.print("Enter Your Choice: ");
    }
    // int choice = scanner.nextInt();
    // System.out.println(); System.out.println();

    // MODIFIES: this
    // EFFECTS: runs the main game loop, processes user input
    private static void runMain() {
        boolean keepGoing = true;
        int choice = 0;

        while (keepGoing) {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                battle();
            } else if (choice == 2) {
                editRoster();
            } else if (choice == 3) {
                editItems();
            } else if (choice == 4) {
                keepGoing = false;
                System.out.println("You have quit the game.");
                quit();
            } else {
                handleInvalidChoice();
            }
        }
    }

    // EFFECTS: saves the roster to file
    private static void saveRoster() {
        try {
            jsonWriter.open();
            jsonWriter.write(roster);
            jsonWriter.close();
            System.out.println("Saved " + "roster" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads roster from file
    private static void loadRoster() {
        try {
            roster = jsonReader.read();
            System.out.println("Loaded " + "roster" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // REQUIRES: choice is none of given
    // EFFECTS: handles invalid choice
    private static void handleInvalidChoice() {
        System.out.println("Invalid Choice");
    }

    // EFFECTS: displays edit roster menu
    public static void displayEditRoster() { // starts game
        System.out.println("Welcome to Main Menu. Please select an option (1, 2, 3 or 4)");
        System.out.println("1. Battle");
        System.out.println("2. Edit Roster");
        System.out.println("3. Edit Items");
        System.out.println("4. Quit");
        System.out.print("Enter Your Choice: "); //TODO: fix the edit roster menu
    }

    // MODIFIES: this
    // EFFECTS: roster menu, processes user input
    private static void editRoster() {
        boolean keepGoing = true;
        int choice = 0;

        while (keepGoing) {
            displayEditRoster();
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.print("Your current roster: " + roster.viewRoster() + "\n");
            } else if (choice == 1) {
                addCharacter();
            } else if (choice == 2) {
                removeCharacter();
            } else if (choice == 3) {
                saveRoster();
            } else if (choice == 4) {
                loadRoster();
            } else if (choice == 5) {
                keepGoing = false;
                displayMainMenu();
            } else {
                handleInvalidChoice();
            }
        }
    }

    // REQUIRES: roster size < 2
    // MODIFIES: roster
    // EFFECTS: add to the roster based on selection
    private static void addCharacter() {
        System.out.println("Select a character to add:");
        characterDatabase.displayCharacterDatabase();
        int characterIndex = scanner.nextInt(); // read user input
        scanner.nextLine();

        if (characterIndex > 0 && characterIndex <= characterDatabase.characterDatabase.size()) {
            EnneagramCharacter character = characterDatabase.characterDatabase.get(characterIndex - 1);
            roster.addCharacter(character);
            System.out.println("Character added to roster.");
        }
    }

    // REQUIRES: roster size > 0
    // MODIFIES: roster
    // EFFECTS: removes from the roster based on selection
    private static void removeCharacter() {
        System.out.println("Select a character to remove:");
        List<String> characterNames = roster.viewRoster();
        List<EnneagramCharacter> characters = roster.getRoster();

        for (int i = 0; i < characterNames.size(); i++) {
            System.out.println((i + 1) + ". " + characterNames.get(i));
        }

        int characterIndex = scanner.nextInt();
        scanner.nextLine();

        if (characterIndex > 0 && characterIndex <= characters.size()) {
            EnneagramCharacter character = characters.get(characterIndex - 1);
            roster.removeCharacter(character);
            System.out.println("Character removed from roster.");
        } else {
            handleInvalidChoice();
        }
    }

    // EFFECTS: item menu, processes user input
    public static void editItems() {
        boolean keepGoing = true;
        int choice = 0;

        while (keepGoing) {
            System.out.println("1. Add item to character");
            System.out.println("2. Remove Item from Character");
            System.out.println("3. Return to main menu");
            System.out.println("4. View inventories");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addItemToCharacter();
            } else if (choice == 2) {
                removeItemFromCharacter();
            } else if (choice == 3) {
                keepGoing = false;
                displayMainMenu();
            } else if (choice == 4) {
                viewInventories();
            } else {
                handleInvalidChoice();
            }
        }
    }

    // REQUIRES: roster size > 0
    // MODIFIES: character.inventory
    // EFFECTS: add item to character depending on selection
    private static void addItemToCharacter() {
        System.out.println("Select a character to add an item to:");
        List<String> characterNames = roster.viewRoster();
        for (int i = 0; i < characterNames.size(); i++) {
            System.out.println((i + 1) + ". " + characterNames.get(i));
        }
        int characterIndex = scanner.nextInt();
        scanner.nextLine();

        if (characterIndex > 0 && characterIndex <= characterNames.size()) {
            EnneagramCharacter character = roster.getRoster().get(characterIndex - 1);
            System.out.println("Available items:");
            for (Item item : items.getItems()) {
                System.out.println(item.getName() + " - " + item.getDesc());
            }
            int itemChoice = scanner.nextInt();
            scanner.nextLine();

            if (itemChoice > 0 && itemChoice <= items.getItems().size()) {
                Item selectedItem = items.getItems().get(itemChoice - 1);
                character.addItem(selectedItem);
                System.out.println(selectedItem.getName() + " added to " + character.getName() + "'s inventory.");
            } // no room to add invalid choice
        }
    }

    // REQUIRES: roster size > 0
    // MODIFIES: character.inventory
    // EFFECTS: remove item from character depending on selection
    private static void removeItemFromCharacter() {
        System.out.println("Select a character to remove an item from:");
        List<String> characterNames = roster.viewRoster();
        for (int i = 0; i < characterNames.size(); i++) {
            System.out.println((i + 1) + ". " + characterNames.get(i));
        }
        int characterIndex = scanner.nextInt();
        scanner.nextLine();

        if (characterIndex > 0 && characterIndex <= characterNames.size()) {
            EnneagramCharacter character = roster.getRoster().get(characterIndex - 1);
            System.out.println("Current inventory:");
            for (Item item : character.getInventory()) {
                System.out.println(item.getName() + " - " + item.getDesc());
            }
            int itemChoice = scanner.nextInt();
            scanner.nextLine();

            if (itemChoice > 0 && itemChoice <= character.getInventory().size()) {
                Item selectedItem = character.getInventory().get(itemChoice - 1);
                character.removeItem(selectedItem);
                System.out.println(selectedItem.getName() + " removed from " + character.getName() + "'s inventory.");
            } // no room to add invalid choice
        }
    }

    // REQUIRES: roster size > 0
    // EFFECTS: views the inventory of a selected character
    private static void viewInventories() {
        System.out.println("Select a character to view their inventory:");
        List<String> characterNames = roster.viewRoster();
        for (int i = 0; i < characterNames.size(); i++) {
            System.out.println((i + 1) + ". " + characterNames.get(i));
        }
        int characterIndex = scanner.nextInt();
        scanner.nextLine();

        if (characterIndex > 0 && characterIndex <= characterNames.size()) {
            EnneagramCharacter character = roster.getRoster().get(characterIndex - 1);
            List<String> inventoryList = character.viewInventory();
            System.out.println("Inventory of " + character.getName() + ":");
            for (String item : inventoryList) {
                System.out.println(item);
            } // no room to add invalid choice
        }
    }

    // EFFECTS: starts a battle
    public static void battle() {
        List<EnneagramCharacter> characters = roster.getRoster();
        if (characters.isEmpty()) {
            System.out.println("You have no characters in your roster, you will die :C.");
            displayMainMenu(); // removed 1 character condition
        }
        enemy = new Enemy("Giant Bob", 300, 15); // will use database later
        System.out.println("\n" + enemy.getName() + " Appears!!");

        while (enemy.isEnemyAlive()) {
            for (EnneagramCharacter character : characters) {
                if (character.getHealth() > 0) {
                    System.out.println("It's " + character.getName() + "'s turn.");
                    displayBattleMenu(character);
                    if (scanner.nextInt() == 1) {
                        character.attack(enemy);
                    }
                }
            }
            if (enemy.isEnemyAlive()) {
                enemyTurn();
            }
        }
        System.out.println("A good fight!");
        runMain();
    }

    //
    // } else if (choice == 2) {
    // // useAbility(character); // currently unavailble :C
    //
    // } else if (choice == 3) {
    // // useItem(character); // currently unavailble :C
    //

    // EFFECTS: displays battle menu
    private static void displayBattleMenu(EnneagramCharacter character) {
        System.out.println("Your HP: " + character.getHealth() + " | Energy: " + character.getEnergy());
        System.out.println("Enemy HP: " + enemy.getHealth());
        System.out.println("What will you do?");
        System.out.println("1. Auto Attack");
        System.out.println("2. Ability (UNAVAILABLE)"); // UNAVAILABLE
        System.out.println("3. Use Items (UNAVAILABLE)"); // UNAVAILABLE
        System.out.print("Enter Your Choice: ");
    }

    // MODIFIES: character.health
    // EFFECT: enemy's turn to attack
    private static void enemyTurn() {
        Random random = new Random();
        int enemyAttackDamage = random.nextInt(10) + 5;
        for (EnneagramCharacter character : roster.getRoster()) {
            if (character.getHealth() > 0) {
                character.setHealth(character.getHealth() - enemyAttackDamage);
                System.out.println("Enemy attacked! " + character.getName() + " took " + enemyAttackDamage
                        + " DMG, " + character.getName() + "'s health updated to " + character.getHealth());
                break;
            }
        }
    }

}
// BELOW is framework:
// private static void useAbility(EnneagramCharacter character) {
// Random random = new Random();
// int abilityDamage = random.nextInt(20) + 10;
// if (character.getEnergy() >= 10) {
// System.out.println(character.getName() + " casted an Ability!");
// character.setEnergy(character.getEnergy() - 10);
// enemyHealth -= abilityDamage;
// System.out.println("Ability costed 10 Mana.");
// System.out.println("Enemy took " + abilityDamage + " DMG, enemy health
// updated to " + enemyHealth);
// } else {
// System.out.println(character.getName() + " doesn't have enough Mana to use
// Ability!");
// }
// }

// private static void useItem(EnneagramCharacter character) {
// if (character.getInventory().size() > 0) {
// System.out.println("Select an item to use:");
// for (int i = 0; i < character.getInventory().size(); i++) {
// System.out.println((i + 1) + ". " +
// character.getInventory().get(i).getName());
// }
// int itemIndex = scanner.nextInt();
// scanner.nextLine();
// Item item = character.getInventory().get(itemIndex - 1);
// character.useItem(item);
// System.out.println(character.getName() + " used " + item.getName() + ".");
// } else {
// System.out.println(character.getName() + " has no items right now.");
// }
// }

// // Loop until the enemy dies
// while (enemyIsAlive && playerHealth > 0) {
// System.out.println("Your HP: " + playerHealth + " | Mana: " + playerMana);
// System.out.println("Enemy HP: " + enemyHealth);
// System.out.println("What will you do?");
// System.out.println("1. Auto Attack");
// System.out.println("2. Ability");
// System.out.println("3. Use Items");
// System.out.print("Enter Your Choice: ");
// int choice = scanner.nextInt();
// scanner.nextLine();

// if (choice == 1) {
// // Auto attack logic
// int attackDamage = random.nextInt(10) + 5; // Random damage between 5 and 15
// System.out.println("You attacked!");
// playerMana += 5; // Gained mana
// enemyHealth -= attackDamage;
// System.out.println("You gained 5 Mana.");
// System.out.println("Enemy took " + attackDamage + " DMG, enemy health updated
// to " + enemyHealth);
// } else if (choice == 2) {
// // Ability logic
// int abilityDamage = random.nextInt(20) + 10; // Random ability damage between
// 10 and 30
// if (playerMana >= 10) {
// System.out.println("You casted an Ability!");
// playerMana -= 10; // Ability costs 10 mana
// enemyHealth -= abilityDamage;
// System.out.println("Ability costed 10 Mana.");
// System.out.println("Enemy took " + abilityDamage + " DMG,
// enemy health updated to " + enemyHealth);
// } else {
// System.out.println("Not enough Mana to use Ability!");
// }
// } else if (choice == 3) {
// // Using items
// System.out.println("You have no items right now.");
// }

// // check monster health
// if (enemyHealth <= 0) {
// System.out.println("Congrats, you won!");
// enemyIsAlive = false;
// break;
// }

// // Enemy's turn
// int enemyAttackDamage = random.nextInt(10) + 5; // Random damage between 5
// and 15
// playerHealth -= enemyAttackDamage;
// System.out.println("Enemy attacked! You took " + enemyAttackDamage
// + " DMG, your health updated to " + playerHealth);

// // check player health
// if (playerHealth <= 0) {
// System.out.println("You died!");
// break;
// }
// }
// runMain();
// }
