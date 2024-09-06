package se.lernia.lindstrom.max.entities;

import se.lernia.lindstrom.max.items.Item;

public class Monster extends Player {
    private final Item loot;

    private Monster(String name, int health, Position position, int attack, Item loot, int defense) {
        super(name, health, position, attack, defense);
        this.loot = loot;
    }

    public static Monster of(String name, int health, Position position, int attack, Item loot, int defense) {
        return new Monster(name, health, position, attack, loot, defense);
    }

    public Item getLoot() {
        return loot;
    }
}