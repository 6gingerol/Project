package ui;

import java.util.ArrayList;
import java.util.List;

import model.Enemy;

// currently not used
public class EnemyDatabase {

    private List<Enemy> enemies;

    public EnemyDatabase() {
        enemies = new ArrayList<>();
        loadEnemies();
    }

    public void loadEnemies() {

        enemies.add(new Enemy("Bob", 50, 5));
        enemies.add(new Enemy("Big Bob", 150, 20));
        enemies.add(new Enemy("Giant Bob", 300, 40));
    }

}
