package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Item;

public class ItemDatabase {
    private static List<Item> items;

    public ItemDatabase() {
        items = new ArrayList<>();
        loadItems();
    }

    public void loadItems() {
        items = new ArrayList<>();
        items.add(new Item("Big Potion", "increases current health by 10", 20));
        items.add(new Item("Yummy Banana", "increases current health by 10", 10));
        items.add(new Item("Scrumptious Borcht", "increases current health by 20", 25));
    }

    public List<Item> getItems() {
        return items;
    }
}
